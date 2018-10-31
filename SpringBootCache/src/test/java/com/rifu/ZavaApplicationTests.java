package com.rifu;

import com.rifu.entity.User;
import com.rifu.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZavaApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisTemplate<Object,User> userRedisTemplate;

    /**
     * string,list,set,hash,zset
     */
    @Test
    public void testRedis01(){
        stringRedisTemplate.opsForValue().append("msg","one");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
    }

    @Test
    public void testRedis02(){
//        User user = userMapper.selectByPrimaryKey(1);
        /*redisTemplate.opsForValue().set("emp-id-1",user);
        Object o = redisTemplate.opsForValue().get("emp-id-1");
        if(o instanceof User){
            System.out.println(o);
        }*/
        //使用redis默认的序列化规则
//        userRedisTemplate.opsForValue().set("user-id-01",user);
        User user1 = userRedisTemplate.opsForValue().get("user-id-01");
        System.out.println(user1);
    }

    @Test
    public void contextLoads() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
    }

}
