package com.example.redistest.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 转json
 */
@Configuration
public class JacksonUtil {

    Logger log= LoggerFactory.getLogger(JacksonUtil.class);

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 对象转换为字符串
     * @param obj
     * @return
     */
    public String writeAsString(Object obj) {
        try {
            String s = objectMapper.writeValueAsString(obj);
            return s;
        } catch (JsonProcessingException e) {
            log.error("object convert json exception,obj:{}", obj);
            throw new RuntimeException(e);
        }
    }

    /**
     * json字符串转为对象
     * @param str
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T readValue(String str, Class<T> tClass) {
        try {
            return objectMapper.readValue(str, tClass);
        } catch (JsonProcessingException e) {
            log.error("str convert Object exception,str:{},class:{}", str, tClass);
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串转集合
     * @param str
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> List<T> readValueList(String str, Class<T> tClass) {
        try {
            TypeFactory typeFactory = TypeFactory.defaultInstance();
            return objectMapper.readValue(str, typeFactory.constructParametricType(List.class, tClass));
        } catch (JsonProcessingException e) {
            log.error("str convert List<Object> exception,str:{},class:{}", str, tClass);
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param str
     * @param tClass
     * @param vClass
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> Map<K, V> readValueMap(String str, Class<K> tClass, Class<V> vClass) {
        try {
            TypeFactory typeFactory = TypeFactory.defaultInstance();
            return objectMapper.readValue(str, typeFactory.constructParametricType(HashMap.class, tClass, vClass));
        } catch (JsonProcessingException e) {
            log.error("str convert Map<K,V> exception,str:{},tClass:{},vClass:{}", str, tClass, vClass);
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串转Map
     * @param str
     * @return
     */
    public Map<String, String> readValueMap(String str) {
        try {
            return objectMapper.readValue(str, HashMap.class);
        } catch (JsonProcessingException e) {
            log.error("str convert Map exception,str:{}", str);
            throw new RuntimeException(e);
        }
    }


}
