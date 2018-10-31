package com.rifu;

import com.rifu.entity.CustomMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZavaApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void contextLoads() {

    }

    /**
     * 点对点发送
     */
    @Test
    public void sendP2P(){
        Map<String, Object> map = new HashMap<>();
        map.put("msg","this is the entity of msg");
        map.put("data",Arrays.asList("one",2,true));

        rabbitTemplate.convertAndSend("exchange.direct","zava.news",map);
    }

    /**
     * 点对点接受
     */
    @Test
    public void receiveP2P(){
        Object o = rabbitTemplate.receiveAndConvert("zava.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 广播的方式发送消息
     */
    @Test
    public void sendBoardCast(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new CustomMsg(1,1,"Custom msg"));
    }

    /**
     * 使用amqpAdmin创建exchange
     */
    @Test
    public void testCreateExchange(){
        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.directExchange"));
    }

    /**
     * 使用amqpAdmin创建queue
     */
    @Test
    public void testCreateQueue(){
        amqpAdmin.declareQueue(new Queue("amqpAdmin.queue",true));
    }

    /**
     * 使用amqpAdmin创建binding规则
     */
    @Test
    public void testCreateBinding(){
        amqpAdmin.declareBinding(new Binding("amqpAdmin.queue",
                Binding.DestinationType.QUEUE,
                "amqpAdmin.directExchange",
                "amqpAdmin.abc",null
                ));
    }

}
