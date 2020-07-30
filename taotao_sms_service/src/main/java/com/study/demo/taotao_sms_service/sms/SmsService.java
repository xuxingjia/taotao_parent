package com.study.demo.taotao_sms_service.sms;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.study.demo.taotao_pojo.params.SmsParams;
import com.study.demo.taotao_pojo.result.SmsResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value = "classpath:sms.properties")
public class SmsService {

    @Value("${accessKeyId}")
    private String accessKeyId;

    @Value("${accessKeySecret}")
    private String accessKeySecret;

    @JmsListener(destination = "sms.queue")
    public void smsService(String jsonString) {
        SmsParams smsParams = JSONObject.parseObject(jsonString, SmsParams.class);
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", smsParams.getPhoneNumbers());
        request.putQueryParameter("SignName", smsParams.getSignName());
        request.putQueryParameter("TemplateCode", smsParams.getTemplateCode());
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + smsParams.getTemplateParam() + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            SmsResult smsResult = JSONObject.parseObject(response.getData(), SmsResult.class);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
