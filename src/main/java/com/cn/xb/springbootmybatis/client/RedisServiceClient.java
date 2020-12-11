package com.cn.xb.springbootmybatis.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: springbootmybatis
 * @Package: com.cn.xb.springbootmybatis.client
 * @ClassName: RedisServiceClient
 * @Author: huiqb
 * @Description: Redis相关实现
 * @Date: 2020/12/10 17:18
 * @Version: 1.0
 */
// @Component：标注Spring管理的Bean，使用@Component注解在一个类上，表示将此类标记为Spring容器中的一个Bean。可以使用@Autowired自动注入
@Component
public class RedisServiceClient {
    private Logger logger= LoggerFactory.getLogger(RabbitMqServiceClient.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate; // 操作Key-Value都是字符串的 因为String类型常用所以单独拆分出来一个
    @Autowired
    RedisTemplate redisTemplate; // 操作Key-Value都是对象的 其中包含了StringRedisTemplate

    //RedisTemplate的直接方法
    /**
     * @Method deleteByKey
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO 根据key删除数据
     * @Param key 键
     * @Return Boolean
     * @Date 2020/12/11
     */
    public Boolean deleteByKey(Object key){
        Boolean delete = redisTemplate.delete(key);
        return delete;
    }

    /**
     * @Method deleteByKey
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO 根据key集合删除数据
     * @Param keys key集合
     * @Return Boolean
     * @Date 2020/12/11
     */
    public Boolean deleteByKey(List<Object> keys){
        redisTemplate.delete(keys);
        return true;
    }

    /**
     * @Method expire
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO 指定key的过期时间
     * @Param key 键
     * @Param time 过期时间
     * @Param timeUnit TimeUnit是java.util.concurrent包下面的一个类，表示给定单元粒度的时间段
        TimeUnit.DAYS          //天
        TimeUnit.HOURS         //小时
        TimeUnit.MINUTES       //分钟
        TimeUnit.SECONDS       //秒
        TimeUnit.MILLISECONDS  //毫秒
     * @Return Boolean
     * @Date 2020/12/11
     */
    public Boolean expire(Object key,Long time,TimeUnit timeUnit){
        Boolean expire = redisTemplate.expire(key, time, timeUnit);
        return expire;
    }

    /**
     * @Method getCacheTimeByKey
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO 根据key获取数据过期时间
     * @Param key 键
     * @Return Long过期时间毫秒值
     * @Date 2020/12/11
     */
    public Long getCacheTimeByKey(Object key){
        Long expire = redisTemplate.getExpire(key);
        return expire;
    }

    /**
     * @Method getCacheTimeByKey
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO 根据key获取数据过期时间并换算成指定单位
     * @Param key 键
     * @Param timeUnit TimeUnit是java.util.concurrent包下面的一个类，表示给定单元粒度的时间段
        TimeUnit.DAYS          //天
        TimeUnit.HOURS         //小时
        TimeUnit.MINUTES       //分钟
        TimeUnit.SECONDS       //秒
        TimeUnit.MILLISECONDS  //毫秒
     * @Return Long过期时间毫秒值
     * @Date 2020/12/11
     */
    public Long getCacheTimeByKey(Object key,TimeUnit timeUnit){
        Long expire = redisTemplate.getExpire(key, timeUnit);
        return expire;
    }

    /**
     * @Method checkByKey
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO 检查key是否存在
     * @Param key 键
     * @Return Boolean
     * @Date 2020/12/11
     */
    public Boolean checkByKey(Object key){
        Boolean hasKey = redisTemplate.hasKey(key);
        return hasKey;
    }


    //TODO String类型相关操作
    /**
     * @Method saveStringValue
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO 向redis里存入String类型数据不设置缓存时间
     * @Param key 键
     * @Param value值
     * @Return Boolean
     * @Date 2020/12/11
     */
    public Boolean saveStringValue(Object key,Object value){
        //1、通过redisTemplate设置值
        //redisTemplate.boundValueOps("StringKey").set("StringValue");
        //redisTemplate.boundValueOps("StringKey").set("StringValue",1, TimeUnit.MINUTES);

        //2、通过BoundValueOperations设置值
        //BoundValueOperations stringKey = redisTemplate.boundValueOps("StringKey");
        //stringKey.set("StringVaule");
        //stringKey.set("StringValue",1, TimeUnit.MINUTES);

        //3、通过ValueOperations设置值
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    /**
     * @Method saveStringValue
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO 向redis里存入String类型数据并设置缓存时间
     * @Param key 键
     * @Param value值
     * @Param time 过期时间毫秒值
     * @Param timeUnit TimeUnit是java.util.concurrent包下面的一个类，表示给定单元粒度的时间段
        TimeUnit.DAYS          //天
        TimeUnit.HOURS         //小时
        TimeUnit.MINUTES       //分钟
        TimeUnit.SECONDS       //秒
        TimeUnit.MILLISECONDS  //毫秒
     * @Return Boolean
     * @Date 2020/12/11
     */
    public Boolean saveStringValue(Object key,Object value,Long time,TimeUnit timeUnit){
        //1、通过redisTemplate设置值
        //redisTemplate.boundValueOps("StringKey").set("StringValue");
        //redisTemplate.boundValueOps("StringKey").set("StringValue",1, TimeUnit.MINUTES);

        //2、通过BoundValueOperations设置值
        //BoundValueOperations stringKey = redisTemplate.boundValueOps("StringKey");
        //stringKey.set("StringVaule");
        //stringKey.set("StringValue",1, TimeUnit.MINUTES);

        //3、通过ValueOperations设置值
        redisTemplate.opsForValue().set(key,value,time, timeUnit);
        return true;
    }

    /**
     * @Method getStringValueByKey
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO String类型根据key获取Value
     * @Param key 键
     * @Return value值
     * @Date 2020/12/11 
     */
    public Object getStringValueByKey(Object key) {
        //1、通过redisTemplate设置值
        //String str1 = (String) redisTemplate.boundValueOps("StringKey").get();

        //2、通过BoundValueOperations获取值
        //BoundValueOperations stringKey = redisTemplate.boundValueOps("StringKey");
        //String str2 = (String) stringKey.get();

        //3、通过ValueOperations获取值
        Object value = redisTemplate.opsForValue().get(key);
        return value;
    }

    /**
     * @Method appendValue
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO 根据指定key在后面拼接value，如key不存在则新建一个
     * @Param key 键
     * @Param value值
     * @Return Boolean
     * @Date 2020/12/11 
     */
    public Boolean appendValue(Object key,String value){
        redisTemplate.opsForValue().append(key,value);
        return true;
    }

    /**
     * @Method incrementStringValueByKey
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO String类型自增或自减Vaule
     * @Param key 键
     * @Param l 自增或自减量 如：1L或-1L
     * @Return 
     * @Date 2020/12/11 
     */
    public Long incrementStringValueByKey(Object key,Long l){
        Long increment = redisTemplate.boundValueOps(key).increment(l);
        return increment;
    }


    //TODO Hash类型相关操作
    /**
     * @Method saveHashValue
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO 向redis里存入Hash类型数据
     * @Param key 键
     * @Param hashKey 值中map的键
     * @Param hashValue 值中map的value
     * @Return Boolean
     * @Date 2020/12/11
     */
    public Boolean saveHashValue(Object key,Object hashKey,Object hashValue){
        //1、通过redisTemplate设置值
        //redisTemplate.boundHashOps("HashKey").put("SmallKey", "HashVaue");

        //2、通过BoundValueOperations设置值
        //BoundHashOperations hashKey = redisTemplate.boundHashOps("HashKey");
        //hashKey.put("SmallKey", "HashVaue");

        //3、通过ValueOperations设置值
        redisTemplate.opsForHash().put(key, hashKey, hashValue);
        return true;
    }

    /**
     * @Method saveHashValue
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO 向redis里存入Hash类型添加一个Map集合
     * @Param key 键
     * @Param map 值
     * @Return Boolean
     * @Date 2020/12/11 
     */
    public Boolean saveHashValue(Object key, Map map){
        redisTemplate.opsForHash().putAll(key,map);
        return true;
    }

    /**
     * @Method getHashValueByKey
     * @Author huiqb
     * @Version  1.0
     * @Description //TODO Hash类型根据key获取Value
     * @Param key 键
     * @Return set 值
     * @Date 2020/12/11
     */
    public Set getHashValueByKey(Object key){
        //1、通过redisTemplate获取值
        //Set set = redisTemplate.boundHashOps("HashKey").keys();

        //2、通过BoundValueOperations获取值
        //BoundHashOperations hashKey = redisTemplate.boundHashOps("HashKey");
        //Set set = hashKey.keys();

        //3、通过ValueOperations获取值
        Set set = redisTemplate.opsForHash().keys(key);
        return set;
    }
}
