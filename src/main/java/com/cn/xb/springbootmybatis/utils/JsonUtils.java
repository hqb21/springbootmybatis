package com.cn.xb.springbootmybatis.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Json操作相关
 */
public class JsonUtils {
    private static Logger logger= LoggerFactory.getLogger(JsonUtils.class);

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 将对象转换成json字符串
    public static String objectToJson(Object data) {
        String json = null;
    	try {
    	    json=MAPPER.writeValueAsString(data);
			return json;
		} catch (JsonProcessingException e) {
    	    logger.error("将对象转换成json字符串失败:",e);
		}
    	return json;
    }
    
    // 将json结果集转化为对象
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        T t=null;
        try {
            t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            logger.error("将json结果集转化为对象失败:",e);
        }
        return t;
    }
    
    // 将json数据转换成pojo对象list
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        List<T> list=null;
        try {
    		list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
            logger.error("将json数据转换成pojo对象list:",e);
		}
    	return list;
    }
    
}
