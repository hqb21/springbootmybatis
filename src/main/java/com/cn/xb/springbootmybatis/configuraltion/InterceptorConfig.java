package com.cn.xb.springbootmybatis.configuraltion;

import com.cn.xb.springbootmybatis.interceptor.SpringBootInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 自定义拦截器的配置类
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private SpringBootInterceptor springBootInterceptor;

    // 自定义拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
            表示拦截所有的请求
            addPathPatterns("/**")
            不需要拦截的请求
             excludePathPatterns("/login", "/register")
         */
        registry.addInterceptor(springBootInterceptor).addPathPatterns("/**").excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    // 自定义资源映射配置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
