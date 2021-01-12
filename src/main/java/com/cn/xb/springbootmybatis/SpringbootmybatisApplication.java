package com.cn.xb.springbootmybatis;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// SpringBoot启动注解
@SpringBootApplication
// SpringBoot自动扫描Mapper映射文件
@MapperScan("com.cn.xb.springbootmybatis.dao")
// 自动开启 SwaggerBean自动装配的相关注解
@EnableSwagger2
// 自动开启 SwaggerBean增强UI自动装配
@EnableSwaggerBootstrapUI
// 自动开启 RabbitMq自动装配的相关注解
@EnableRabbit
// 自动开启 自动装配自定义WEB模块 SpringMvc环境将被你完全接管    慎用！！！    用了需在扩展的类中重写父类的方法即可（想想xml的时代缺啥加啥）@EnableWebMvc+extends WebMvcConfigurerAdapter
//@EnableWebMvc
public class SpringbootmybatisApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmybatisApplication.class, args);
    }

    //继承 SpringBootServletInitializer 类，重写 configure 方法，目到：Spring Boot打成war包，pom文件还要做相应的修改
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootmybatisApplication .class);
    }

}
