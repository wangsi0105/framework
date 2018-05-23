/**
 * 文件名：RedisUtils.java
 * 创建日期：  2016年5月7日
 * 作者：      Administrator
 * 版权所有(C) 2016-2017 深圳市华康全景信息技术有限公司
 * 保留所有权利.
 */
package com.ws.framework.common.cache;


import com.ws.framework.common.cache.redis.RedisCacheFactory;
import com.ws.framework.common.utils.GsonUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 功能描述：缓存操作实现.外部调用工具类
 * @author lizhangxiong 2016年5月29日
 */
public class CachedUtils
{

    public static void set(String key, Object value)
    {
        // TODO Auto-generated method stub
        RedisCacheFactory.getWriteInstance().set(key, value);
    }

    public static void set(String key, Object value, long timout)
    {
        RedisCacheFactory.getWriteInstance().set(key, value, timout);
    }

    public static void setx(byte[] key, byte[] value, int timout)
    {
        RedisCacheFactory.getWriteInstance().setx(key, value, timout);
    }
    
    /**
     * 获取所有key集合
     * @param keys 模糊查询可用key*
     * @return
     */
    public static Set<String> keys(String keys){
    	return RedisCacheFactory.getWriteInstance().getKeys(keys);
    }

    public static String get(String key)
    {
        // TODO Auto-generated method stub
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().get(key);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().get(key);
        }
    }

    public static byte[] get(byte[] key)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().get(key);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().get(key);
        }
    }

    public static String hget(String key, String field)
    {
        // TODO Auto-generated method stub
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().hget(key, field);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().hget(key, field);
        }

    }

    public static byte[] hget(byte[] mapKey, byte[] field)
    {
        // TODO Auto-generated method stub
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().hget(mapKey, field);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().hget(mapKey, field);
        }

    }

    public static <T> T get(String key, Class<T> cls)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().get(key, cls);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().get(key, cls);
        }
    }

    public static Boolean exists(String key)
    {
        // TODO Auto-generated method stub
        return RedisCacheFactory.getWriteInstance().exists(key);
    }

    public static Boolean exists(byte[] key)
    {
        // TODO Auto-generated method stub
        return RedisCacheFactory.getWriteInstance().exists(key);
    }

    public static Long lpush(String key, Object value)
    {
        // TODO Auto-generated method stub
        return RedisCacheFactory.getWriteInstance().lpush(key, value);
    }

    public static Long lpush(String key, Object value, long timout)
    {
        // TODO Auto-generated method stub
        return RedisCacheFactory.getWriteInstance().lpush(key, value, timout);
    }

    public static String rpop(String key)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().rpop(key);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().rpop(key);
        }
    }

    public static <T> T rpop(String key, Class<T> cls)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().rpop(key, cls);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().rpop(key, cls);
        }
    }

    public static Long llen(String key)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().llen(key);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().llen(key);
        }
    }

    public static List<String> lrange(String key, long start, long end)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().lrange(key, start, end);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().lrange(key, start, end);
        }
    }

    public static <T> T lrange(String key, long start, long end, Class<T> cls)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().lrange(key, start, end, cls);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().lrange(key, start, end, cls);
        }
    }

    public static Long del(String key)
    {
        // TODO Auto-generated method stub
        return RedisCacheFactory.getWriteInstance().del(key);
    }

    public static Long del(byte[] key)
    {
        return RedisCacheFactory.getWriteInstance().del(key);
    }

    public static Long del(String... keys)
    {
        return RedisCacheFactory.getWriteInstance().del(keys);
    }

    public static Long delObject(String key)
    {
        return RedisCacheFactory.getWriteInstance().delObject(key);
    }
    
    public static Long delObject(String[] keys)
    {
        return RedisCacheFactory.getWriteInstance().delObject(keys);
    }

    public static Set<byte[]> getKeys(String keyPrefix)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().getKeysByte(keyPrefix);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().getKeysByte(keyPrefix);
        }
    }

    public static Set<String> getKeysByString(String keyPrefix)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().getKeys(keyPrefix);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().getKeys(keyPrefix);
        }
    }

    public static void hmset(String key, Object value)
    {
        RedisCacheFactory.getWriteInstance().hmset(key, value);
    }

    public static void hset(String key, String filed, String value)
    {
        RedisCacheFactory.getWriteInstance().hset(key, filed, value);
    }

    public static void expire(String key, long time)
    {
        RedisCacheFactory.getWriteInstance().expire(key, time);
    }

    public static void hmsetOfMap(String key, Map<String, String> values)
    {
        RedisCacheFactory.getWriteInstance().hmsetOfMap(key, values);
    }

    public static void hmsetOfMap(byte[] key, Map<byte[], byte[]> values)
    {
        RedisCacheFactory.getWriteInstance().hmsetOfMap(key, values);
    }

    public static <T> T hmget(String key, Class<T> cls, String... filed)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().hmget(key, cls, filed);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().hmget(key, cls, filed);
        }
    }

    public static <T> T hgetAll(String key, Class<T> cls)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().hgetAll(key, cls);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().hgetAll(key, cls);
        }
    }

    public static <T> Map<String, T> hgetAllOfMap(String key, Class<T> cls)
    {
        Map<String, String> map = null;
        if (RedisCacheFactory.isExistSlave)
        {
            map = RedisCacheFactory.getReadInstance().hgetAll(key);
        }
        else
        {
            map = RedisCacheFactory.getWriteInstance().hgetAll(key);
        }
        if (map != null && !map.isEmpty())
        {
            Map<String, T> resultMap = new LinkedHashMap<String, T>();
            for (String filed : map.keySet())
            {
                resultMap.put(filed, GsonUtil.toBean(map.get(filed), cls));
            }
            return resultMap;
        }
        return null;
    }

    public static Map<String, String> hgetAllString(String key)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().hgetAll(key);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().hgetAll(key);
        }
    }

    public static boolean hdel(String key, String filed)
    {
        return RedisCacheFactory.getWriteInstance().hdel(key, filed);
    }

    public static boolean hdel(byte[] mapKey, byte[] field)
    {
        return RedisCacheFactory.getWriteInstance().hdel(mapKey, field);
    }

    public static Object getObject(String key)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().getObject(key);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().getObject(key);
        }
    }

    public static Object getObject(byte[] key)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().getObject(key);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().getObject(key);
        }
    }

    public static void setObject(String key, Object value, long timeout)
    {
        RedisCacheFactory.getWriteInstance().setObject(key, value, timeout);
    }

    public static long delLikeKey(String keyPrefix)
    {
        return RedisCacheFactory.getWriteInstance().delLikeKey(keyPrefix);
    }

    public static boolean sadd(String key, String member)
    {
        return RedisCacheFactory.getWriteInstance().sadd(key, member);
    }

    public static Set<String> smembers(String key)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().smembers(key);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().smembers(key);
        }
    }

    public static long srem(String key, String [] member)
    {
        return RedisCacheFactory.getWriteInstance().srem(key, member);
    }

    public static boolean hexists(String key, String filed)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().hexists(key, filed);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().hexists(key, filed);
        }
    }

    public static Map<String, Object> getObjectMap(String key)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().getObjectMap(key);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().getObjectMap(key);
        }
    }

    public static Map<byte[], byte[]> getObjectMap(byte[] mapKey)
    {
        if (RedisCacheFactory.isExistSlave)
        {
            return RedisCacheFactory.getReadInstance().getObjectMap(mapKey);
        }
        else
        {
            return RedisCacheFactory.getWriteInstance().getObjectMap(mapKey);
        }
    }

    public static void setMapObjectPut(String key, Map<String, Object> value, long timeout)
    {
        RedisCacheFactory.getWriteInstance().setMapObjectPut(key, value, timeout);
    }
}
