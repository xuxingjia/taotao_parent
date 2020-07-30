package com.study.demo.taotao_user_web.controller;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.CommonReturnType;
import com.study.demo.taotao_pojo.params.SmsParams;
import com.study.demo.taotao_pojo.pojo.TbUser;
import com.study.demo.taotao_user_interface.service.RegisterService;
import com.study.demo.taotao_user_web.common.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/registerController")
@PropertySource(value = "classpath:sms.properties", encoding = "UTF-8")
public class RegisterController extends BaseController {

    @Reference
    private RegisterService registerService;

    @Value("${SIGN_NAME}")
    private String signName;

    @Value("${TEMPLATE_CODE}")
    private String templateCode;

    @RequestMapping(value = "/smsCode", method = RequestMethod.POST, consumes = "application/json")
    public CommonReturnType smsCode(@RequestBody SmsParams smsParams) throws BusinessException {
        smsParams.setSignName(signName);
        smsParams.setTemplateCode(templateCode);
        boolean sendRsoult = registerService.smsCode(smsParams);
        if (!sendRsoult) {
            return CommonReturnType.create("系统繁忙,请稍后再试!!!", 201);
        } else {
            return CommonReturnType.create("发送成功!");
        }
    }

    @RequestMapping(value = "/refisterUser", method = RequestMethod.POST, consumes = "application/json")
    public CommonReturnType refisterUser(@RequestBody TbUser tbUser) throws BusinessException {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        tbUser.setCreated(Timestamp.valueOf(dateFormat.format(new Date())));
        tbUser.setUpdated(Timestamp.valueOf(dateFormat.format(new Date())));
        int registerStatus = registerService.registerUser(tbUser);
        switch (registerStatus) {
            case 0:
                return CommonReturnType.create("验证码输入错误!!!",201);
            case 1:
                return CommonReturnType.create("注册成功!!!");
            case -3:
                return CommonReturnType.create("验证码已过期,请重新发送!!!",201);
            case -2:
                return CommonReturnType.create("验证码不存在,请先发送验证码!!!",201);
            default:
                return CommonReturnType.create("");
        }
    }
}