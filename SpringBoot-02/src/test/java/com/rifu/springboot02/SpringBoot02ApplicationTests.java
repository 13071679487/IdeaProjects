package com.rifu.springboot02;

import com.rifu.springboot02.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot02ApplicationTests {

    @Autowired
    Person person;

    Logger logger = LoggerFactory.getLogger(SpringBoot02ApplicationTests.class);

    @Test
    public void contextLoads() {
        System.out.println(person);

    }

    @Test
    public void testLogging(){

        //日志的级别，由低到高
        //trace < debug <info <warn <error
        logger.trace("这是trace 日志");
        logger.debug("这是debug日志");
        //SpringBoot 默认使用的是info级别
        logger.info("这是info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");

    }

}
