package com.study.demo.taotao_common.common;

import java.io.Serializable;

public class CommonReturnType<T> implements Serializable {

    private Integer code;
    private T data;
    private String massage;

    //定义一个通用方法

    public static <T> CommonReturnType<T> create(T result) {
        return CommonReturnType.create(result, 200);
    }

    public static <T> CommonReturnType<T> create(T result, Integer code) {
        return CommonReturnType.create(result, code, "");
    }

    public static <T> CommonReturnType<T> create(T result, String massage) {
        return CommonReturnType.create(result, 200, massage);
    }

    public static <T> CommonReturnType<T> create(T result, Integer code, String massage) {
        CommonReturnType<T> returnType = new CommonReturnType<>();
        returnType.setData(result);
        returnType.setCode(code);
        returnType.setMassage(massage);
        return returnType;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
