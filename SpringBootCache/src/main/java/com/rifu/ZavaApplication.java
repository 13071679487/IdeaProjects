package com.rifu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan(value = "com.rifu.mapper")
@SpringBootApplication
@EnableCaching
public class ZavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZavaApplication.class, args);
    }
}
