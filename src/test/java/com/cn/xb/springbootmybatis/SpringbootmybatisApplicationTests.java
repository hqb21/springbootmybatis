package com.cn.xb.springbootmybatis;

import com.cn.xb.springbootmybatis.dao.TestDepartmentDao;
import com.cn.xb.springbootmybatis.entity.TestDepartment;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootmybatisApplicationTests {
    @Autowired
    TestDepartmentDao d;

    @Test
    void contextLoads() {
        TestDepartment byId = d.queryById("1");
        System.out.println(byId);
    }

}
