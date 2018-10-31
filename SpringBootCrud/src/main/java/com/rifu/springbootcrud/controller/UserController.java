package com.rifu.springbootcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author Rifu
 * @Date 2018/9/16  0:08
 */
@Controller
public class UserController {

    @PostMapping("/user/login")
    public String login(HttpSession session, @RequestParam("username") String username,
                        @RequestParam("password")String password,
                        Map<String,Object> map){
        if(!StringUtils.isEmpty(username)  && "123456".equals(password)){
            session.setAttribute("currentUser",username);
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名密码错误");
            return "login";
        }
    }
}
