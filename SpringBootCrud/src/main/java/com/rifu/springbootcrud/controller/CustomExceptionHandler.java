package com.rifu.springbootcrud.controller;

import com.rifu.springbootcrud.exception.UserNotExistException;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Rifu
 * @Date 2018/9/16  19:59
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * 客户端和流浪器返回的都是json
     * @param e
     * @return
     */
    /*
    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public  Map<String,Object> handleException(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notexist");
        map.put("msg",e.getMessage());
        return map;
    }
    */

    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e , HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //需要在这里传入自己的错误状态码，不然得到的返回码是200，得到的视图解析也不是我们自定义的4xx,5xx
        request.setAttribute("javax.servlet.error.status_code",500);

        map.put("code","user.notexist");
        map.put("msg",e.getMessage());

        request.setAttribute("ext",map);
        return "forward:/error";
    }
}
