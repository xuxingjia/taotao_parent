package com.study.demo.taotao_user_interface.service;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_pojo.params.SmsParams;
import com.study.demo.taotao_pojo.pojo.TbUser;

public interface RegisterService {

    /**
     * 注册账号
     * @param tbUser 注册信息
     * @return 返回注册状态
     */
    int registerUser(TbUser tbUser) throws BusinessException;

    /**
     * 注册短信验证码
     * @param params 验证码需要信息
     * @return 验证码
     */
    boolean smsCode(SmsParams params) throws BusinessException;
}
