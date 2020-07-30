package com.study.demo.taotao_common.common;

public class BusinessException extends Exception {

    private String errorMassage;
    private  Integer errorCode;

    public BusinessException(Integer errorCode,String errorMassage){
        super();
        this.errorCode=errorCode;
        this.errorMassage=errorMassage;
    }

    public String getErrorMassage() {
        return errorMassage;
    }

    public void setErrorMassage(String errorMassage) {
        this.errorMassage = errorMassage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}