/*
 * 文 件 名:  JsonUilts.java
 * 版    权:  深圳市华康全景信息技术有限公司
 * 描    述:  Ftp工具類
 * 修 改 人:  路猷
 * 修改时间:  2016年4月16日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ws.framework.common.utils;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Json工具类
 * 
 * @author luyou
 * @version 1.0
 * @created 27-11-2014 16:02:13
 */
public class JsonUilts
{
    
    /**
     * json字符串转对象
     * 
     * @param json json串
     * @param calss 类类型
     * @return
     */
    public static <T> T jsonToBean(String json, Class<T> calss, Map<String, Class<T>> classMap)
    {
        return GsonUtil.toBean(json, calss);
    }
    
    /**
     * json字符串转对象
     * 
     * @param json json串
     * @param calss 类类型
     * @return
     * @throws ParseException
     */
    public static <T> T jsonToBean(String json, Class<T> calss)
        throws ParseException
    {
        return GsonUtil.toBean(json, calss);
    }
    
    /**
     * 
     * Map转json
     * 
     * @param map 键值对象
     * @return json
     * @since V1.0.0
     */
    public static String mapToJson(Map<String, Object> map)
    {
        return GsonUtil.toJson(map);
    }
    
    /**
     * 
     * 对象转json
     * 
     * @param obj 对象
     * @return
     * @since V1.0.0
     */
    public static String beanToJson(Object obj)
    {
        return GsonUtil.toJson(obj);
    }
    
    /**
     * 
     * list转json
     * 
     * @param list 数据容器
     * @return json
     * @since V1.0.0
     */
    public static String listToJson(List<Map<String, Object>> list)
    {
        return GsonUtil.toJson(list);
    }
}
