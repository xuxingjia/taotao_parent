package com.study.demo.taotao_pojo.result;

import java.io.Serializable;

public class SmsResult implements Serializable {


    /**
     * Message : OK
     * RequestId : 873043ac-bcda-44db-9052-2e204c6ed20f
     * BizId : 607300000000000000^0
     * Code : OK
     */

    private String Message;
    private String RequestId;
    private String BizId;
    private String Code;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String RequestId) {
        this.RequestId = RequestId;
    }

    public String getBizId() {
        return BizId;
    }

    public void setBizId(String BizId) {
        this.BizId = BizId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }
}
