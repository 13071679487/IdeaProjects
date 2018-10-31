package com.rifu.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * 主从复制
 */
public class MasterAndSlaveDemo {
    @Test
    public void test(){
        Jedis master=new Jedis("127.0.0.1",6379);
        Jedis slave=new Jedis("127.0.0.1",6380);
        slave.slaveof("127.0.0.1",6379);
        master.set("master","tangseng");

        System.out.println(slave.get("master"));    //有可能内存操作太快，导致获取不到数据，实际上以完成了写入的操作
    }
}
