package com.rifu.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 测试jedis的使用api
 */
public class APIDemo {
    Jedis jedis = new Jedis("127.0.0.1", 6379);

    @Test
    public void testString(){
        jedis.set("k1","v1");
        Set<String> keys = jedis.keys("*");
        for (String str:keys
             ) {
                System.out.println("str : "+str);
        }
    }

    @Test
    public void testSet(){
        jedis.sadd("rifu","1","2","3");
        Set<String> set = jedis.smembers("rifu");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(" : "+next);
        }

        jedis.srem("rifu","2");
        System.out.println("size : "+jedis.smembers("rifu").size());
    }

    @Test
    public void testZSet(){
        jedis.zadd("zset",100,"a");
        jedis.zadd("zset",90,"b");
        jedis.zadd("zset",80,"e");
        jedis.zadd("zset",70,"d");
        jedis.zadd("zset",60,"f");
        Set<String> zset = jedis.zrange("zset", 0, -1);
        Iterator<String> iterator = zset.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(""+next);
        }
        Set<String> zset1 = jedis.zrangeByScore("zset", 60, 90);
        Iterator<String> iterator1 = zset1.iterator();
        while (iterator1.hasNext()){
            String next = iterator1.next();
            System.out.println(""+next);
        }
    }

    @Test
    public void testHash(){
        jedis.hset("rifu1","username","rifu");
        System.out.println("rifu : "+jedis.hget("rifu1","username"));
        HashMap<String, String> map = new HashMap<>();
        map.put("gender","man");
        map.put("phone","13071679487");
        map.put("email","528428122@qq.com");
        jedis.hmset("lili",map);
        List<String> result = jedis.hmget("lili", "gender", "phone", "email");
        for (String str:result
             ) {
            System.out.println("value : "+str);
        }
    }
}
