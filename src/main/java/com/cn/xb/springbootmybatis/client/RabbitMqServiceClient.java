package com.cn.xb.springbootmybatis.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProjectName: springbootmybatis
 * @Package: com.cn.xb.springbootmybatis.client
 * @ClassName: RabbitMqServiceClient
 * @Author: huiqb
 * @Description: rabbitmq的具体实现
 * @Date: 2020/12/3 16:47
 * @Version: 1.0
 */
// @Component：标注Spring管理的Bean，使用@Component注解在一个类上，表示将此类标记为Spring容器中的一个Bean。可以使用@Autowired自动注入
@Component
public class RabbitMqServiceClient {
    private Logger logger= LoggerFactory.getLogger(RabbitMqServiceClient.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //监听消息队列中增加数据立刻消费
    @RabbitListener(queues ={"queues.news","test.queues","test.queues.emps","test.queues.news"})
    public <T>List<T> listenerRabbitmq(List<T> list){
        logger.info("Rabbitmq监听队列数据新增："+list);
        return list;
    }

    /**
     * @Method contextLoads
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO rabbitMq生产消息至消息队列
     * @Param exchange:交换器- 可以选择使用单播的交换器或广播的交换器或选择的交换器
     * @Param routingKey:路由键值：选择具体通过哪个路由传至指定的交换器，选择了广播的交换器路由键值可以为空字符串
     * @Param object:需要生产的消息
     * @Return
     * @Date 2020/12/8
     */
    public boolean contextLoads(String exchange,String routingKey,Object object) {
        try {
            logger.info("开始生产消息至Rabbitmq,交换器："+exchange+";路由键值："+routingKey+";消息："+object);
            // exchage:交换器；；message需要自己构造一个；可以定制消息内容和消息头；
            // rabbitTemplate.send(exchage,routeKey,message);

            // object默认当成消息体，只需要传入要发送的对象自动序列化发送给rabbitmq；
            // rabbitTemplate.convertAndSend(exchage,routeKey,object);

            // 对象被默认序列化后发送出去
            rabbitTemplate.convertAndSend(exchange,routingKey,object);
            return true;
        }catch (Exception e){
            logger.error("开始生产消息至Rabbitmq失败：",e);
            return false;
        }
    }

    /**
     * @Method receive
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO 获取rabbitmq消息并消费
     * @Param queuesName消费的队列名称
     * @Return
     * @Date 2020/12/8
     */
    public <T>List<T> receive(String queuesName){
        List<T> list =null;
        try{
            list=(List<T>) rabbitTemplate.receiveAndConvert(queuesName);
            return list;
        }catch (Exception e){
            logger.error("获取Rabbitmq消息失败：",e);
            return list;
        }
    }
}
