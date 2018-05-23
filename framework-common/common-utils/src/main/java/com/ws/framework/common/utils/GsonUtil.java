/**
 * 文件名：GsonUtil.java
 * 创建日期：  2016年7月25日
 * 作者：      lizhangxiong
 * 版权所有(C) 2016-2017 深圳市华康全景信息技术有限公司
 * 保留所有权利.
 */
package com.ws.framework.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 功能描述： 谷歌gson组件操作封装
 * @author lizhangxiong 2016年7月25日
 */
public class GsonUtil
{
    private static Gson gson = null;
    static
    {
        if (gson == null)
        {
            gson = /* new Gson(); */ new GsonBuilder().registerTypeAdapter(new TypeToken<Date>()
            {
            }.getType(), new JsonDeserializer<Date>()
            {

                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                        throws JsonParseException
                {
                    try
                    {
                        String jsonStr = json.getAsString();
                        if (json instanceof JsonPrimitive)
                        {
                            if (((JsonPrimitive) json).isNumber())
                            {
                                return new Date(Long.parseLong(jsonStr));
                            }
                        }

                        return DateUtil.stringToDate(jsonStr);
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    return null;
                }

            }).setDateFormat(DateUtil.YEAR_MONTH_DAY_HH_MM_SS)
                    .registerTypeAdapter(new TypeToken<TreeMap<String, Object>>()
                    {
                    }.getType(), new JsonDeserializer<TreeMap<String, Object>>()
                    {
                        @Override
                        public TreeMap<String, Object> deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException
                        {

                            TreeMap<String, Object> treeMap = new TreeMap<>();
                            JsonObject jsonObject = json.getAsJsonObject();
                            Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                            for (Map.Entry<String, JsonElement> entry : entrySet)
                            {
                                treeMap.put(entry.getKey(), entry.getValue());
                            }
                            return treeMap;
                        }
                    }).create();

        }

    }

    private GsonUtil()
    {
    }

    /**
     * 转成json
     * 
     * @param object
     * @return
     */
    public static String toJson(Object object)
    {
        String json = null;
        if(object==null)
        {
    		return json;
        }
    	if (gson != null)
        {
            json = gson.toJson(object);
        }
        return json;
    }

    /**
     * 转成bean
     * 
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T toBean(String json, Class<T> cls)
    {
        T t = null;
        if(StringUtils.isEmpty(json))
        {
        	return t;
        }
        if (gson != null)
        {
            t = gson.fromJson(json, cls);
        }
        return t;
    }

    /**
     * 转成bean
     * 
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T toBean(String json, Type typeOfT)
    {
        T t = null;
        if(StringUtils.isEmpty(json)||typeOfT==null)
        {
        	return t;
        }
        if (gson != null)
        {
            t = gson.fromJson(json, typeOfT);
        }
        return t;
    }

    /**
     * 转成list
     * 
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> cls)
    {
        List<T> list = null;
        if(StringUtils.isEmpty(json)||cls==null)
        {
        	return list;
        }
        if (gson != null)
        {
            list = gson.fromJson(json, new TypeToken<List<T>>()
            {
            }.getType());
        }
        return list;
    }

    /**
     * 转成list中有map的
     * 
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> toListMaps(String json)
    {
        List<Map<String, T>> list = null;
        if(StringUtils.isEmpty(json))
        {
        	return list;
        }
        if (gson != null)
        {
            list = gson.fromJson(json, new TypeToken<List<Map<String, T>>>()
            {
            }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     * 
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> toMaps(String json)
    {
        Map<String, T> map = null;
        if(StringUtils.isEmpty(json))
        {
        	return map;
        }
        if (gson != null)
        {
            map = gson.fromJson(json, new TypeToken<TreeMap<String, Object>>()
            {
            }.getType());
        }
        return map;
    }
}
