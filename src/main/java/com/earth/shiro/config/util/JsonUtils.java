/**
 *
 */
package com.earth.shiro.config.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    public static ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 对象转换成Json字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("toJson throw exception", e);
        }
        return null;
    }

    /**
     * json转对象
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T toObject(String jsonStr, Class<T> clazz) {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T t = null;
        try {
            t = MAPPER.readValue(jsonStr, clazz);
        } catch (JsonParseException e) {
            LOGGER.error("toObject throw JsonParseException", e);
        } catch (JsonMappingException e) {
            LOGGER.error("toObject throw JsonMappingException", e);
        } catch (IOException e) {
            LOGGER.error("toObject throw IOException", e);
        }
        return t;
    }

    public static <T> List<T> toList(String jsonStr, Class<T> clazz) {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        List rt = null;
        try {
            rt = MAPPER.readValue(jsonStr, javaType);
        } catch (IOException e) {
            LOGGER.error("toList throw IOException", e);
        }
        return rt;
    }


    /**
     * obj to map
     * @param obj
     * @return
     */
    public static HashMap<String, Object> toMap(Object obj) {
        String objJson = toJson(obj);
        HashMap result = null;
        try {
            result = MAPPER.readValue(objJson, HashMap.class);
        } catch (IOException e) {
            LOGGER.error("toMap throw IOException", e);
        }
        return result;
    }

}
