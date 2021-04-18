package com.wsbo.broker.controller;

import lombok.Data;

/**
 * @Author: 项峥
 * @Date: 2021/4/18 21:26
 */
@Data
public class ResponseData<T> {
    private int code;
    private boolean success;
    private String message;
    private T data;

    public static ResponseData<Void> success() {
        ResponseData<Void> responseData = new ResponseData<>();
        responseData.setSuccess(true);
        responseData.setMessage("success");
        return responseData;
    }

    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setSuccess(true);
        responseData.setMessage("success");
        responseData.setData(data);
        return responseData;
    }

    public static ResponseData<Void> fail(String message) {
        ResponseData<Void> responseData = new ResponseData<>();
        responseData.setSuccess(false);
        responseData.setMessage(message);
        return responseData;
    }
}
