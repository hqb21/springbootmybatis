package com.cn.xb.springbootmybatis.configuraltion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.Jackson2XmlMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// RabbitMq配置类
/**
 Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
 Queue:消息的载体,每个消息都会被投到一个或多个队列。
 Routing:路由关键字,exchange根据这个关键字进行消息投递。
 Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
 Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
 vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
 Producer:消息生产者,就是投递消息的程序.
 Consumer:消息消费者,就是接受消息的程序.
 Channel:消息通道(新到),在客户端的每个连接里,可建立多个channel，重服创建tcp连接太过消耗资源，一条独立的双向数据流通道，以复用同一条tcp连接.
 */
/*
  RabbitMQ常用的Exchange Type有三种：fanout、direct、topic。
  fanout:把所有发送到该Exchange的消息投递到所有与它绑定的队列中。广播  一对多
  direct:把消息投递到那些binding key与routing key完全匹配的队列中。单播  一对一
  topic:将消息路由到binding key与routing key模式匹配的队列中。匹配  一对多
* */
@Configuration
public class RabbitMqConfig {

    // @Bean注解用于告诉方法，产生一个Bean对象，然后这个Bean对象交给Spring管理。产生这个Bean对象的方法Spring只会调用一次，随后这个Spring将会将这个Bean对象放在自己的IOC容器中。
    /*
        @Scope是spring中ioc的一个作用域，具有以下几种不同的作用域
        1.singleton 单例模式：全局有且仅有一个实例
        2.prototype 原型模式：每次获取这个Bean对象时都会创建一个新的实例
        3.request 表示该针对每一次HTTP请求都会产生一个新的bean，同时该bean仅在当前HTTP request内有效
        4.session 表示该针对每一次HTTP请求都会产生一个新的bean，同时该bean仅在当前HTTP session内有效
        5.global session 不常用
        request、session、global session 均不常用，都属于WEB作用域；
        此注解只能作用在Bean对象上
     */
    // @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    // 更改RabbitMq序列化方式，将默认的jdk序列化方式改为json序列化
    @Bean
    public MessageConverter messageConverter(){
        // json：Jackson2JsonMessageConverter
        return new Jackson2JsonMessageConverter();
    }



}