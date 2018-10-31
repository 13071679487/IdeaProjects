package com.rifu;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableRabbit
@SpringBootApplication
public class ZavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZavaApplication.class, args);
    }
}
