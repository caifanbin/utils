package com.binge.utils.util;

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

}
