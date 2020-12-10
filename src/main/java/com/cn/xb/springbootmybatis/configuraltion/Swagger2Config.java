package com.cn.xb.springbootmybatis.configuraltion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

// Swagger2的配置类
@Configuration
public class Swagger2Config {
    // swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    // @Bean注解用于告诉方法，产生一个Bean对象，然后这个Bean对象交给Spring管理。产生这个Bean对象的方法Spring只会调用一次，随后这个Spring将会将这个Bean对象放在自己的IOC容器中。
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.cn.xb.springbootmybatis.controller"))
                // 过滤的地址
                .paths(PathSelectors.any())
                .build();
    }
    // 构建api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title("标题")
                //描述
                .description("描述")
                //版本
                .version("1.0")
                //创建人
                .contact(new Contact("回钦宝","https://blog.csdn.net/weixin_45082687","885506524@qq.com"))
                //执行
                .build();
    }
}
