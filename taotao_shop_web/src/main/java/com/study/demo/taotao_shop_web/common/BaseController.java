package com.study.demo.taotao_shop_web.common;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.CommonReturnType;
import com.study.demo.taotao_common.common.ErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BaseController {

    /**
     *  错误处理
     * @param ex 抛出的异常
     * @return 返回封装后的异常数据
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private Object exceptionHandler(Exception ex) {
        CommonReturnType commonReturnType = new CommonReturnType();
        if (ex instanceof BusinessException) {
            BusinessException exception = (BusinessException) ex;
            commonReturnType.setCode(exception.getErrorCode());
            commonReturnType.setMassage(exception.getErrorMassage());
        }else {
            commonReturnType.setCode(ErrorEnum.UNKNOWN_ERROR);
            commonReturnType.setMassage("未知错误!!!");
        }
        return commonReturnType;
    }
}
