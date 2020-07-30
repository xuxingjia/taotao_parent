package com.study.demo.taotao_pojo.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValidatorResult {

    //判断是否有错误
    private boolean isError = false;

    //存储错误信息
    private Map<String, String> errorMap = new HashMap<>();

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    /**
     * 获取错误信息
     * @return 错误信息
     */
    public String getErrorMassage() {
        return StringUtils.join(errorMap.values().toArray(), ",");
    }
}
