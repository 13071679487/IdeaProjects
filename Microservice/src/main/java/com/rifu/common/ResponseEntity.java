package com.rifu.common;


import java.io.Serializable;

/**
 * @Author Rifu
 * @Date 2018/9/28  0:15
 */
public class ResponseEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private T data;

    ResponseEntity(){}


    private ResponseEntity(int code){
        this.code=code;
    }

    private ResponseEntity(int code, T data){
        this.code=code;
        this.data=data;
    }

    private ResponseEntity(int code , String msg){
        this.code=code;
        this.msg=msg;
    }

    private ResponseEntity(int code, String msg, T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

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

    public static <T> ResponseEntity<T> createBySuccess(){
        return new ResponseEntity<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ResponseEntity<T> createBySuccess(T data){
        return new ResponseEntity<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ResponseEntity<T> createBySuccessWithMessage(String msg){
        return new ResponseEntity<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ResponseEntity<T> createBySuccess(String msg, T data){
        return new ResponseEntity<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ResponseEntity<T> createByError(){
        return new ResponseEntity<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ResponseEntity<T> createByErrorWithMessage(String errorMsg){
        return new ResponseEntity<T>(ResponseCode.ERROR.getCode(),errorMsg);
    }

    public static <T> ResponseEntity<T> createByError(int errorCode, String errorMsg){
        return new ResponseEntity<T>(errorCode,errorMsg);
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
