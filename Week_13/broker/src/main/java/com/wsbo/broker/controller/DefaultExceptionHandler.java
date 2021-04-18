package com.wsbo.broker.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 项峥
 * @Date: 2021/4/18 21:37
 */
@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public ResponseData<Void> runtimeException(RuntimeException e) {
        return ResponseData.fail(e.getMessage());
    }
}
