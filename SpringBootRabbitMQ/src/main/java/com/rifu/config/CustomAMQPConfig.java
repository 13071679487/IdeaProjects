package com.rifu.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Rifu
 * @Date 2018/10/17  13:38
 */
@Configuration
public class CustomAMQPConfig {

    /**
     * 替换掉原来默认使用的jdk序列化器，改成支持json传输数据的方式
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
