package com.rifu.controller;

import com.rifu.common.ResponseEntity;
import com.rifu.service.ZavaMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Rifu
 * @Date 2018/10/27  22:49
 */
@RestController
@RequestMapping("/mail")
public class ZavaMailController {

    @Autowired
    ZavaMailService zavaMailService;

    @RequestMapping("/has_send")
    public ResponseEntity hasSend(@RequestParam("to")String to){
        ResponseEntity responseEntity = zavaMailService.sendMailSuccess(to);
        return responseEntity;
    }
}
