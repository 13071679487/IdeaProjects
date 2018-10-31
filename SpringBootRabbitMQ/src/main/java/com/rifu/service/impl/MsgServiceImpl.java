package com.rifu.service.impl;

import com.rifu.entity.CustomMsg;
import com.rifu.service.IMsgService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author Rifu
 * @Date 2018/10/17  13:49
 */
@Service
public class MsgServiceImpl implements IMsgService {

    @RabbitListener(queues = "zava.news")
    public void receiveCustomMsg(CustomMsg msg){
        System.out.println(msg);
    }
}
