package io.kimmking.rpcfx.exception;

public class BizException extends RuntimeException{
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BizException() {
    }

    public BizException(ErrorCode errorCode) {
        code = errorCode.getCode();
        message = errorCode.getMessage();
    }
}
