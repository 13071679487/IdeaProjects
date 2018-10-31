package com.rifu.test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDataTest {

    private ApplicationContext ac= null;
    {
        ac= new ClassPathXmlApplicationContext("applicationContext.xml");

    }

    public static void main(String [] args){
           testDataSource();
    }

    public static  void testDataSource(){

    }
}
