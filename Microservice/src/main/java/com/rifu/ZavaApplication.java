package com.rifu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@MapperScan("com.rifu.mapper")
@SpringBootApplication
public class ZavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZavaApplication.class, args);
    }
}
