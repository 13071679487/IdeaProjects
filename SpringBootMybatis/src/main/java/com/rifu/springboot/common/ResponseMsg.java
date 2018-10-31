package com.rifu.springboot.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @Author Rifu
 * @Date 2018/9/28  0:15
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseMsg<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private T data;

    private ResponseMsg(int code){
        this.code=code;
    }

    private ResponseMsg(int code,T data){
        this.code=code;
        this.data=data;
    }

    private ResponseMsg(int code ,String msg){
        this.code=code;
        this.msg=msg;
    }

    private ResponseMsg(int code,String msg,T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    @JsonIgnore
    public boolean success(){
        return this.code==ResponseCode.SUCCESS.getCode();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> ResponseMsg<T> createBySuccess(){
        return new ResponseMsg<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ResponseMsg<T> createBySuccess(T data){
        return new ResponseMsg<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ResponseMsg<T> createBySuccessWithMessage(String msg){
        return new ResponseMsg<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ResponseMsg<T> createBySuccess(String msg, T data){
        return new ResponseMsg<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ResponseMsg<T> createByError(){
        return new ResponseMsg<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ResponseMsg<T> createByErrorWithMessage(String errorMsg){
        return new ResponseMsg<T>(ResponseCode.ERROR.getCode(),errorMsg);
    }

    public static <T> ResponseMsg<T> createByError(int errorCode,String errorMsg){
        return new ResponseMsg<T>(errorCode,errorMsg);
    }

}
