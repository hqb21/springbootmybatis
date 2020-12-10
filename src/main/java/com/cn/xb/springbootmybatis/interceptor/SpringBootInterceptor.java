package com.cn.xb.springbootmybatis.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 自定义拦截器
// @Component：标注Spring管理的Bean，使用@Component注解在一个类上，表示将此类标记为Spring容器中的一个Bean。可以使用@Autowired自动注入
@Component
public class SpringBootInterceptor implements HandlerInterceptor {
    //生成SLF4日志工厂
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 进入拦截器所要做的操作
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("~~~进入拦截器~~~");
        logger.info("拦截器放行");
        return true;
    }

    // 进入拦截器之前所要做的操作
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    // 进入拦截器之后所要做的操作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
