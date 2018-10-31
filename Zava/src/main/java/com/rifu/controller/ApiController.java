package com.rifu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Rifu
 * @Date 2018/10/11  13:30
 */
@Controller
public class ApiController {

    /**
     * 服务器维护中
     * @return
     */
    @GetMapping("/maintain")
    public String toMaintainServer(){
        return "error/maintain";
    }

    @GetMapping("/about")
    public String toAboutme(){
        return "about";
    }
}
