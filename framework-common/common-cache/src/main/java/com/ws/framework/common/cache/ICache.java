package com.ws.framework.common.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2018/5/22.
 */
public interface ICache {
    /**
     * 设置一个缓存，如果key不存在，新建并保存
     * @param key  缓存key
     * @param value 缓存对象
     */
    void set(String key,Object value);

    /**
     * 设置一个缓存，同时指定过期时间。
     * @param key 缓存key值
     * @param value 缓存对象
     * @param timout 超时时间 （单位/毫秒）
     */
    void set(String key,Object value,long timout);
    /**
     * 设置一个缓存，同时指定过期时间。
     * @param key 缓存key值
     * @param value 缓存对象
     * @param timout 超时时间 （单位/毫秒）
     */
    void setx(byte[] key, byte[] value, int timout);

    /**
     * 获取缓存
     * @param key 缓存key值
     * @return String 返回String对象
     */
    String get(String key);
    /**
     * 获取缓存
     * @param key 缓存key值
     * @return String 返回String对象
     */
    byte[] get(byte[] key);
    /**
     * 获取缓存
     * @param key
     * @param field
     * @return
     */
    String hget(String key, String field);

    /**
     * 获取缓存
     * @param mapKey
     * @param field
     * @return
     */
    byte[] hget(byte[] mapKey, byte[] field);

    /**
     *  获取缓存 ，并将其转换为指定类型。
     * @param key 缓存key值
     * @param cls 缓存对象类型。
     * @return T cls参数指定类型对象
     */
    <T> T get(String key,Class<T> cls);

    /**
     * 获取一个对象，字节序列化
     * @param key 缓存key值
     * @return T cls参数指定类型对象
     */
    Object getObject(String key);

    /**
     * 获取一个对象，字节序列化
     * @param key 缓存key值
     * @return T cls参数指定类型对象
     */
    Object getObject(byte [] key);

    /**
     * 设置一个缓存对象，字节序列化
     * @param key 缓存key值
     * @param value 数据值
     * @param timeout 超时时间
     */
    void setObject(String key, Object value, long timeout);


    /**
     * 获取缓存
     * @param key 缓存key值
     * @return String 返回String对象
     */
    Long del(String key);
    /**
     * 获取缓存
     * @param key 缓存key值
     * @return String 返回String对象
     */
    Long del(byte[] key);

    /**
     * 删除缓存
     * @param keys 缓存key值
     * @return Long
     */
    Long del(String... keys);

    /**
     * 判断缓存key是否存在
     * @param key 缓存key值
     * @return boolean  存在返回true，否则false
     */
    Boolean exists(String key);

    /**
     * 判断缓存key是否存在
     * @param key 缓存key值
     * @return boolean  存在返回true，否则false
     */
    Boolean exists(byte[] key);


    /**
     * 往列表左边存放一个元素
     * @param key 缓存key值
     * @param value 缓存数据
     * @return Long 返回最新队列长度
     */
    Long lpush(String key,Object value);



    /**
     * 往列表左边存放一个元素
     * @param key 缓存key值
     * @param value 缓存数据
     * @param timout 缓存超时时间  （单位/毫秒）
     * @return Long 返回最新队列长度
     */
    Long lpush(String key,Object value,long timout);

    /**
     * 从列表右边弹出元素 返回元素，并删除队列中该元素
     * @param key 缓存key值
     * @return String 缓存对象
     */
    String rpop(String key);

    /**
     * 从列表右边弹出元素 返回元素，并删除队列中该元素
     * @param key 缓存key值
     * @param cls 缓存对象类型。
     * @return T cls参数指定类型对象
     */
    <T> T rpop(String key,Class<T> cls);

    /**
     * 返回列表长度。
     * @param key 缓存key值
     * @return Long 队列长度
     */
    Long llen(String key);

    /**
     * 返回指定范围的元素列表
     * @param key  缓存key值
     * @param start
     * @param end
     * @return  List<String> 缓存对象列表
     */
    List<String> lrange(String key, long start, long end);

    /**
     * 返回指定范围的元素列表
     * @param key
     * @param start  元素的开始位置 从0开始，负数表示从右边开始
     * @param end    元素的结束位置 从0开始，负数表示从右边开始
     * @param cls 缓存对象类型。
     * @return T cls参数指定类型对象列表
     */
    <T> T lrange(String key, long start, long end,Class<T> cls);

    /**
     * 设置一个对象hash缓存，后续可以对每个对象的单个属性进行存取等操作。
     * @param key  数据key
     * @param value 数据对象
     */
    void hmset(String key,Object value);

    /**
     * 设置一个对象hash缓存，后续可以对每个对象的单个属性进行存取等操作。
     * @param key  数据key
     * @param value map<String,String> 直接映射到jedis API
     */
    void hmsetOfMap(String key,Map<String,String> values);

    /**
     * 设置一个对象hash缓存，后续可以对每个对象的单个属性进行存取等操作。
     * @param key  数据key
     * @param values
     */
    void hmsetOfMap(byte[] key, Map<byte[], byte[]> values);

    /**
     * 获取一个hash缓存值
     * @param key 数据key
     * @param filed 属性名列表
     * @return T cls参数指定类型对象
     */
    <T> T hmget(String key,Class<T> cls,String... filed);

    /**
     * 置一个对象hash缓存
     * @param key 数据key
     * @param field 属性名
     * @param value 属性值
     */
    void hset(String key,String field,String value);

    /**
     * 设置过期时间
     * @param key
     * @param time
     */
    void expire(String key,long time);
    /**
     * 获取所有hash属性值
     * @param key 数据key
     * @param cls 返回对象类型
     * @return T cls参数指定类型对象
     */
    <T> T hgetAll(String key, Class<T> cls);
    /**
     * 获取所有hash属性值
     * @param key 数据key
     * @return map<String,String> 直接映射到jedis API
     */
    Map<String,String> hgetAll(String key);

    /**
     * 根据前缀模糊获取key
     * @param keyPrefix key前缀
     * @return Set<String> 值
     */
    Set<String> getKeys(String keyPrefix);

    /**
     * 获取一个对象
     * @param key 缓存key值
     * @return  Map<String, Object>
     */
    Map<String, Object> getObjectMap(String key);

    /**
     * 获取一个对象
     * @param mapKey 缓存key值
     * @return  Map<String, Object>
     */
    Map<byte[], byte[]> getObjectMap(byte[] mapKey);

    /**
     * 设置一个hash对象
     * @param key 缓存key值
     * @param value  缓存对象
     * @param timeout  超时时间，单位毫秒
     * @return String  返回 OK成功，其它失败。
     */
    void setMapObjectPut(String key, Map<String, Object> value,long timeout);
    /**
     * 删除缓存
     *
     * @param keyPrefix key前缀 做模糊删除用
     * @return
     */
    long delLikeKey(String keyPrefix);

    /**
     * hash属性是否存在
     * @param key  缓存key
     * @param filed  属性列表
     * @return  存在返回true，否则false
     */
    boolean hexists(String key, String filed);


    /**
     * hash 删除
     * @param key  缓存key
     * @param filed  属性列表
     * @return  存在返回true，否则false
     */
    boolean hdel(String key, String filed);
    /**
     * hash 删除
     * @param mapKey  缓存key
     * @param field  属性列表
     * @return  存在返回true，否则false
     */
    boolean hdel(byte[] mapKey, byte[] field);

    /**
     * 增加set元素
     * @param key
     * @param filed
     * @return
     */
    boolean sadd(String key, String member);

    /**
     * 获取所有set元素
     * @param key
     * @return //返回名称为key的set的所有元素
     */
    Set<String> smembers(String key);


    /**
     * 获取所有set元素
     * @param key
     * @return //返回名称为key的set的所有元素
     */
    long srem(String key,String [] member);

    /**
     * 批量删除
     * @param keys
     * @return
     */
    public Long delObject(String [] keys);

}
