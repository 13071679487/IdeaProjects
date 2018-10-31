package com.rifu;

import com.rifu.entity.ZavaMail;
import com.rifu.service.ZavaVerifyCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZavaApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {
    }
    /*
    *//**
     * 点对点发送
     *//*
    @Test
    public void sendP2P(){
        ZavaMail mail = new ZavaMail();
//        mail.setId(1);
        mail.setTitle("title");
        mail.setContent("123456");
        mail.setFrom("528428122@qq.com");
        mail.setTo("13071679487@163.com");
//        mail.setCreateTime(new Date());
//        mail.setUpdateTime(new Date());
        rabbitTemplate.convertAndSend("zava.topic","*.mail",mail);
    }

    @Test
    public void sendVerifyCode(){
        Map<String,String> map=new HashMap<>();
        map.put("phone","13071679487");
        map.put("type","1");
        map.put("verify","528428");
        rabbitTemplate.convertAndSend("zava.topic","*.verifycode",map);
    }*/

}
