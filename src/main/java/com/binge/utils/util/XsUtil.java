package com.binge.utils.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import com.thoughtworks.xstream.security.AnyTypePermission;

/**
 * XStream
 * xml与实体类转换
 */
public class XsUtil {



    private static String XML_TAG = "<?xml version='1.0' encoding='GBK'?> \n";

    /**
     * Description: 私有化构造
     */
    private XsUtil() {
        super();
    }
    /**
     * @Description 为每次调用生成一个XStream
     * @Title getInstance
     * @return
     */
    private static XStream getInstance() {
        XStream xStream = new XStream(new DomDriver("GBK")) {
            /**
             * 忽略xml中多余字段
             */
            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };

        // 设置默认的安全校验
        XStream.setupDefaultSecurity(xStream);
        // 使用本地的类加载器
        xStream.setClassLoader(XsUtil.class.getClassLoader());
        // 允许所有的类进行转换
        xStream.addPermission(AnyTypePermission.ANY);
        return xStream;
    }

    /**
     * @Description 将xml字符串转化为java对象
     * @Title xmlToBean
     * @param xml
     * @param clazz
     * @return
     */
    public static <T> T xmlToBean(String xml, Class<T> clazz) {
        XStream xStream = getInstance();
        xStream.processAnnotations(clazz);
        Object object = xStream.fromXML(xml);
        T cast = clazz.cast(object);
        return cast;
    }

    /**
     * @Description 将java对象转化为xml字符串
     * @Title beanToXml
     * @param object
     * @return
     */
    public static String beanToXml(Object object) {
        XStream xStream = getInstance();
        xStream.processAnnotations(object.getClass());
        // 剔除所有tab、制表符、换行符
        String xml = xStream.toXML(object);
        return xml;
    }

    /**
     * @Description 将java对象转化为xml字符串（包含xml头部信息）
     * @Title beanToXml
     * @param object
     * @return
     */
    public static String beanToXmlWithTag(Object object) {
        String xml = XML_TAG + beanToXml(object);
        return xml;
    }


}
