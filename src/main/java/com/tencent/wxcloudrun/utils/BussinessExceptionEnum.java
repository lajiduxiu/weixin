package com.tencent.wxcloudrun.utils;

public enum BussinessExceptionEnum {
    SERVER_ERROR(500,"服务器异常！"),
    ;
    private Integer code;
    private String message;

    BussinessExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
