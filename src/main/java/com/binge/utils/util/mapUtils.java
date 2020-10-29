package com.binge.utils.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * map相关工具类
 * @Authror fanbin CAI
 * @Date 2020/10/29 10:11
 */
public class mapUtils {

    /**
     * 反转map的key和value
     * @param map
     * @return
     */
    public static Map<String,String> reverseMap(Map<String,?> map){
        HashMap<String,String> dictionary=new HashMap<>();

        Set<String> keys = map.keySet();

        for(String key : keys){
            String value = map.get(key).toString();
            dictionary.put(value,key);
        }
        return dictionary;
    }

}
