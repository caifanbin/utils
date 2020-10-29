package com.binge.utils.util;


import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.Map;

/**
 * json相关工具类
 * @Authror fanbin CAI
 * @Date 2020/10/29 10:08
 */
public class JsonUtils {

    /**
     * 读取json文件转为map
     * @return
     */
    public static Map<String,String> wirteJson(String filePath){
        BufferedReader reader = null;
        String laststr = "";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        reader = new BufferedReader(inputStreamReader);
        String tempString = null;
        while (true) {
            try {
                if (!((tempString = reader.readLine()) != null)){ break;}
            } catch (IOException e) {
                e.printStackTrace();
            }
            laststr += tempString;
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map maps = (Map) JSON.parse(laststr);
        return maps;
    }
}
