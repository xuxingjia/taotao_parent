package com.study.demo.taotao_common.common;

public interface CommonError {

    String getErrorMassage();

    Integer getErrorCode();

    CommonError setErrorMassage(String errorMassage);
}
