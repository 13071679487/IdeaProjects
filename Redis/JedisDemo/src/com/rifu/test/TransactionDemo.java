package com.rifu.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;


public class TransactionDemo {
    Jedis jedis=new Jedis("127.0.0.1",6379);
    @Test
    public void test(){
        Transaction transaction = jedis.multi();
        transaction.set("balance","100");
        transaction.set("debt","0");
        transaction.exec();
        //transaction.discard();        #放弃该事物
    }



    /**
     * CAS  (check and set) 先检查再进行操作
     *
     * 结合 redis的事物进行使用
     */
    @Test
    public void testTransaction(){
        int balance,amount=10;
        jedis.watch("balance");
        balance=Integer.parseInt(jedis.get("balance"));
        if (balance < amount){
            jedis.unwatch();
            System.out.println("余额不足");
        }
        else {
            System.out.println("begin transaction");
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance",amount);
            transaction.incrBy("debt",amount);
            transaction.exec();
            System.out.println("balance : "+jedis.get("balance"));
            System.out.println("debt : "+jedis.get("debt"));
        }

    }
}
