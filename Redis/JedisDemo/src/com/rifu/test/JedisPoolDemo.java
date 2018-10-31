package com.rifu.test;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolDemo {
    public static void main(String [] args){
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = jedisPool.getResource();
        try {

            jedis.set("suprise","ture");
        }catch (Exception e ){
            e.printStackTrace();
        }
        finally {
            JedisPoolUtil.release(jedisPool,jedis);
        }
    }
}
