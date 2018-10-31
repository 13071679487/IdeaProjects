package com.rifu.common;

/**
 * @Author Rifu
 * @Date 2018/9/28  0:25
 */
public enum  ResponseCode {
    SUCCESS(100,"SUCCESS"),
    ERROR(200,"ERROR"),
    ILLEGAL_ARG(301,"ILLEGAL_ARG"),
    UNAUTHORIZATION(402,"UNAUTHORIZATION");

    private final int code;
    private final String desc;

    ResponseCode(int code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
