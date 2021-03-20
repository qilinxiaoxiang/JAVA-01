package io.kimmking.rpcfx.exception;

public enum ErrorCode {
    CONNECT_FAIL(10001, "连接失败"),
    SYSTEM_ERROR(10002, "系统异常");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
