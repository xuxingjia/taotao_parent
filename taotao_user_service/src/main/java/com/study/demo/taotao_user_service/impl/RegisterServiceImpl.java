package com.study.demo.taotao_user_service.impl;

import com.alibaba.fastjson.JSONObject;
import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.ErrorEnum;
import com.study.demo.taotao_common.common.utils.JedisInterface;
import com.study.demo.taotao_dao.mapper.UserMapper;
import com.study.demo.taotao_pojo.params.SmsParams;
import com.study.demo.taotao_pojo.pojo.TbUser;
import com.study.demo.taotao_pojo.utils.ValidatorImpl;
import com.study.demo.taotao_pojo.utils.ValidatorResult;
import com.study.demo.taotao_user_interface.service.RegisterService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.Destination;
import java.util.Random;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Destination destination;

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private JedisInterface jedisInterface;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int registerUser(TbUser tbUser) throws BusinessException {
        ValidatorResult result = this.validator.validator(tbUser);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        int insertStatus;
        if (!jedisInterface.exists("smsCode")){
            insertStatus=-2;
        }else if (jedisInterface.pTtl("smsCode")>0){
            String smsCode = jedisInterface.get("smsCode");
            if (smsCode.equals(tbUser.getSmsCode())) {
                insertStatus = userMapper.insertTbUser(tbUser);
            }else {
                insertStatus=0;
            }
        }else {
            insertStatus=-3;
        }
        return insertStatus;
    }

    @Override
    public boolean smsCode(SmsParams params) throws BusinessException {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            builder.append(random.nextInt(10));
        }
        params.setTemplateParam(builder.toString());
        String code = builder.toString();
        jedisInterface.setex("smsCode", 3000, code);
        ValidatorResult result = this.validator.validator(params);
        if (result.isError()) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR, result.getErrorMassage());
        }
        String jsonString = JSONObject.toJSONString(params);
        try {
            jmsMessagingTemplate.convertAndSend(destination, jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
