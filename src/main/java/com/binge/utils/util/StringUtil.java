package com.binge.utils.util;

import com.alibaba.fastjson.JSON;
import org.junit.platform.commons.util.StringUtils;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class StringUtil {

    /**
     * 字符串非空判断
     * @param value
     * @return
     */
    public static boolean notNull(String value){
        return value!=null && !value.trim().equals("");
    }

    /**
     * sql的like拼接
     * @param value
     * @return
     */
    public static String toSqlLike(String value){
        return notNull(value) ? "%"+value+"%" : null;
    }

    public static   <T> T ifNull(T ele, T defaultObj){
        return ele==null?defaultObj: ele;
    }

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 占位符替换
     * @param text
     * @param param
     * @return
     */
    public static String  parse(String text,String param){
        if(StringUtils.isBlank(param)){
            return text;
        }
        String start = "${";
        String end = "}";
        Map<String,String> map = JSON.parseObject(param, Map.class);
        Set<String> keys = map.keySet();
        for (String key: keys) {
            text = text.replace(start+key+end, map.get(key));
        }
        return text;
    }
}
