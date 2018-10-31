package com.rifu.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author Rifu
 * @Date 2018/9/21  20:30
 */
@RestController
public class TestController {

    @Autowired
    JdbcTemplate jdbcTemplate;          //该bean是SpringBoot默认自动为我们添加到ioc容器里面的

    @GetMapping("/list")
    public Map<String ,Object> list(){
        List<Map<String, Object>> queryForList = jdbcTemplate.queryForList("select * from dept");
        return queryForList.get(0);
    }
}
