package com.tencent.wxcloudrun.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FastJsonTools {
    private static SerializeConfig CONFIG = null;

    static {

        CONFIG = new SerializeConfig();
        //使用和json-lib兼容的日期输出格式
        CONFIG.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        //使用和json-lib兼容的日期输出格式
        CONFIG.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
    }

    private static final SerializerFeature[] FEATURES = {
            //输出空置字段
            SerializerFeature.WriteMapNullValue,
            //list字段如果为null，输出[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            //数值字段如果为null，输出0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            //Boolean字段如果为null，输出false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            //字符类型字段如果为null，输出""，而不是null
            SerializerFeature.WriteNullStringAsEmpty,
    };

    /**
     * 将对象转换为json字符串
     *
     * @param object
     * @return
     */

    public static String toJSONSFeaturesString(Object object) {
        return JSON.toJSONString(object, CONFIG, FEATURES);
    }

    /**
     * 将对象转换为json字符串
     *
     * @param object
     * @return
     */

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, CONFIG);
    }

    /**
     * json字符串转换为map
     *
     * @param a
     * @return
     */

    public static Map stringTOCollect(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }

    /**
     * 将map转为string
     *
     * @param m
     * @return
     */
    public static String collectToString(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }

    /**
     * 用fastjson将json字符串转换为Javabean
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T getJson(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 用fastjson将json字符串解析成为一个List<Javabean>及List<String>
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> getArrayJson(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 用fastjson将json字符串解析成为:List<Map<String,Object>>
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> getListMap(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
