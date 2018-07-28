package com.airboard.core.exception;

public class ValidateException extends RuntimeException {

    private Integer code;

    private String message;

    public ValidateException(ValidateExceptionEnum validateExceptionEnum) {
        this.code = validateExceptionEnum.getCode();
        this.message = validateExceptionEnum.getMessage();
    }

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
}
