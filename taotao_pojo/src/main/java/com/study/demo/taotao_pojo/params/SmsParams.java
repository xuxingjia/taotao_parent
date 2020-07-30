package com.study.demo.taotao_pojo.params;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class SmsParams implements Serializable {

    @NotBlank(message = "电话号码不能为空!!!")
    private String phoneNumbers;
    @NotBlank(message = "signName不能为空!!!")
    private String signName;
    @NotBlank(message = "templateCode不能为空!!!")
    private String templateCode;
    @NotBlank(message = "templateParam不能为空!!!")
    private String templateParam;

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }
}
