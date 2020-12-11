package com.cn.xb.springbootmybatis;

import com.cn.xb.springbootmybatis.entity.TestDepartment;
import com.cn.xb.springbootmybatis.entity.TestPerson;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootmybatisApplicationTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate; // 操作Key-Value都是字符串的
    @Autowired
    RedisTemplate redisTemplate; // 操作Key-Value都是对象的

    /**
     * redis常见的五大数据类型
     * 1.String（字符串）
     * 2.List（列表）
     * 3.Set（集合）
     * 4.Hash（散列）
     * 5.ZSet（有序集合）
     */
    @Test
    void contextLoads() {
        //操作字符串 redisTemplate.opsForValue();
//        Integer append = stringRedisTemplate.opsForValue().append("test", "你好111");
//        String msg = stringRedisTemplate.opsForValue().get("test");
//        System.out.println(append);
//        System.out.println(msg);
        Long test = redisTemplate.boundValueOps("person").increment(1L);//val做-1操作
        System.out.println(test);

        //操作列表 redisTemplate.opsForList();
//        stringRedisTemplate.opsForList().leftPush("testlist","1");
//        stringRedisTemplate.opsForList().leftPush("testlist","2");
//        stringRedisTemplate.opsForList().leftPush("testlist","3");

        //操作集合 aredisTemplate.opsForSet();
        //stringRedisTemplate.opsForSet();

        //操作散列 redisTemplate.opsForHash();
        //stringRedisTemplate.opsForHash();

        //操作有序集合 redisTemplate.opsForZSet();
        //stringRedisTemplate.opsForZSet();


        //操作对象
//        TestPerson person = new TestPerson();
//        person.setPersonId("10001");
//        person.setPersonName("回钦宝");
//        person.setPersonDepartmentId("101");
////        // 默认保存对象使用jdk序列化机制
//        redisTemplate.opsForValue().set("person",person);
    }

}
