/**
 * 文件名：AbstractRedisCache.java
 * 创建日期：  2016年5月7日
 * 作者：      lizhangxiong
 * 版权所有(C) 2016-2017 深圳市华康全景信息技术有限公司
 * 保留所有权利.
 */
package com.ws.framework.common.cache.redis;

import com.google.common.collect.Maps;
import com.ws.framework.common.cache.CacheRuntimeException;
import com.ws.framework.common.cache.ICache;
import com.ws.framework.common.utils.JsonUilts;
import com.ws.framework.common.utils.ObjectUtils;
import com.ws.framework.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.SafeEncoder;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 功能描述：redis缓存操作
 *
 * @author wangsi 2018年5月23日
 */
public abstract class AbstractRedisCache implements ICache {
	protected static final Logger logger = LoggerFactory.getLogger(AbstractRedisCache.class);

	/**
	 * 获取资源
	 *
	 * @return
	 * @throws JedisException
	 */
	protected abstract Jedis getResource() throws JedisException;

	/**
	 * 释放资源
	 *
	 * @return
	 * @throws JedisException
	 */
	protected abstract void closeResource(Jedis jedis);

	@Override
	public void set(String key, Object value) {
		verify(key);
		verify(value);
		// TODO Auto-generated method stub
		Jedis jedis = getResource();
		String result = null;
		try {
			result = jedis.set(key, objectToString(value));
			if (!"OK".equals(result)) {
				throw new CacheRuntimeException("set filed,result=" + result);
			}
		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("set filed,", e);
		} finally {
			this.closeResource(jedis);
		}
	}

	@Override
	public void set(String key, Object value, long timout) {
		verify(key);
		verify(value);
		Jedis jedis = getResource();
		String result = null;
		try {
			if (timout > 0) {
				result = jedis.setex(key, (int) (timout / 1000), objectToString(value));
			} else {
				result = jedis.set(key, objectToString(value));
			}
			if (!"OK".equals(result)) {
				// throw new CacheRuntimeException("set filed,result=" +
				// result);
			}
		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("set filed,", e);
		} finally {
			this.closeResource(jedis);
		}

	}

	@Override
	public void setx(byte[] key, byte[] value, int timout) {
		verify(key);
		verify(value);
		Jedis jedis = getResource();
		String result = null;
		try {
			result = jedis.setex(key, timout, value);
		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("set filed,", e);
		} finally {
			this.closeResource(jedis);
		}

	}

	@Override
	public String get(String key) {
		verify(key);
		Jedis jedis = getResource();
		String result = null;
		try {
			result = jedis.get(key);
			if (StringUtils.isEmpty(result)) {
				logger.error("result empty,result={}", result);
				// throw new CacheRuntimeException(
				// "result empty,result=" + result);
			}
			return result;
		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("set filed,", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;
	}

	@Override
	public byte[] get(byte[] key) {
		verify(key);
		Jedis jedis = getResource();
		byte[] result = null;
		try {
			return jedis.get(key);
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;
	}

	@Override
	public String hget(String key, String field) {
		verify(key);
		Jedis jedis = getResource();
		String result = null;
		try {
			result = jedis.hget(key, field);
			if (StringUtils.isEmpty(result)) {
				logger.error("result empty,result={}", result);
			}
			return result;
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;

	}

	@Override
	public byte[] hget(byte[] mapKey, byte[] field) {
		Jedis jedis = getResource();
		try {
			return jedis.hget(mapKey, field);
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;

	}

	@Override
	public <T> T get(String key, Class<T> cls) {
		verify(key);
		Jedis jedis = getResource();
		T res = null;
		String result = null;
		try {
			result = jedis.get(key);
			if (StringUtils.isEmpty(result)) {
				// throw new CacheRuntimeException(
				// "result empty,result=" + result);
			}
			res = stringToObject(result, cls);
			return res;
		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("set filed,", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;
	}

	@Override
	public Long del(String key) {
		verify(key);
		Jedis jedis = getResource();

		try {
			return jedis.del(key);

		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("set filed,", e);
		} finally {
			this.closeResource(jedis);
		}
		return 0L;
	}

	@Override
	public Long del(byte[] key) {
		verify(key);
		Jedis jedis = getResource();
		try {
			return jedis.del(key);

		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return 0L;
	}

	@Override
	public Long del(String... keys) {
		Jedis jedis = getResource();
		try {
			return jedis.del(keys);

		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return 0L;
	}

	public Long delObject(String key) {
		verify(key);
		Jedis jedis = getResource();
		try {
			return jedis.del(SafeEncoder.encode(key));
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return 0L;
	}

	public Long delObject(String[] keys) {
		verify(keys);
		Jedis jedis = getResource();
		try {
			byte[][] list = new byte[keys.length][];
			for (int i = 0; i < keys.length; i++) {
				list[i] = SafeEncoder.encode(keys[i]);
			}
			return jedis.del(list);
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return 0L;
	}

	@Override
	public Boolean exists(String key) {
		verify(key);
		Jedis jedis = getResource();
		try {
			return jedis.exists(key);
		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("set filed,", e);
		} finally {
			this.closeResource(jedis);
		}
		return false;
	}

	@Override
	public Boolean exists(byte[] key) {
		verify(key);
		Jedis jedis = getResource();
		try {
			return jedis.exists(key);
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return false;
	}

	/**
	 * 将所有指定的值插入到存于 key 的列表的头部。
	 * 如果 key不存在，那么在进行 push 操作前会创建一个空列表。
	 * 如果 key 对应的值不是一个 list 的话，那么会返回一个错误
	 * @return 在 push 操作后的 list 长度
	 */
	public Long lpush(String key, Object value) {
		verify(key);
		Jedis jedis = getResource();
		try {
			return jedis.lpush(key, value.toString());
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return 0l;
	}

	public Long lpush(String key, Object value,long time) {
		return null;
	}

	@Override
	public String rpop(String key) {
		return null;
	}

	@Override
	public <T> T rpop(String key, Class<T> cls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long llen(String key) {
		verify(key);
		Jedis jedis = getResource();
		try {
			return jedis.llen(key);
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return 0l;
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		verify(key);
		Jedis jedis = getResource();
		try {
			return jedis.lrange(key, start, end);
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return new ArrayList<String>();
	}

	@Override
	public <T> T lrange(String key, long start, long end, Class<T> cls) {
		verify(key);
		Jedis jedis = getResource();
		try {
			return (T) jedis.lrange(key, start, end);
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;
	}

	/**
	 * 验证参数，失败抛出运行时异常
	 *
	 * @param obj
	 *            输入对象
	 */
	private void verify(Object obj) {
		if (null == obj) {
			logger.error("arg is null obj=" + obj);
			throw new CacheRuntimeException("arg is null obj=" + obj);
		}

	}

	private <T> T stringToObject(String src, Class<T> cls) {

		try {
			return (T) JsonUilts.jsonToBean(src, cls);
		} catch (ParseException e) {
			logger.error("Analytic Object failure,", e);
			throw new CacheRuntimeException("Analytic failure,", e);
		}
	}

	/**
	 * 将输入对象转换为String，如果是对象就装换为json
	 *
	 * @param obj
	 * @return String
	 */
	private String objectToString(Object obj) {
		if (obj instanceof String) {
			return (String) obj;
		} else if (obj instanceof StringBuilder) {
			return (String) obj;
		} else if (obj instanceof StringBuffer) {
			return (String) obj;
		} else if (obj instanceof Integer) {
			return obj.toString();
		} else if (obj instanceof Long) {
			return obj.toString();
		} else if (obj instanceof Short) {
			return obj.toString();
		}

		return JsonUilts.beanToJson(obj);
	}

	@Override
	public Set<String> getKeys(String keyPrefix) {
		verify(keyPrefix);
		Jedis jedis = getResource();
		Set<String> value = null;
		try {
			value = jedis.keys(keyPrefix + "*");
			if (null == value) {
				throw new CacheRuntimeException("value empty,result=" + value);
			}
			return value;
		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("set filed,", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;
	}

	public Set<byte[]> getKeysByte(String keyPrefix) {
		verify(keyPrefix);
		Jedis jedis = getResource();
		Set<byte[]> value = null;
		try {
			value = jedis.keys(SafeEncoder.encode(keyPrefix));
			if (null == value) {
				throw new CacheRuntimeException("value empty,result=" + value);
			}
			return value;
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;
	}

	@Override
	public void hmset(String key, Object value) {
		verify(key);
		Jedis jedis = getResource();
		String result = "";
		try {
			HashMap<String, String> keyMap = objectToMap(value);
			result = jedis.hmset(key, keyMap);
		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("hset filed,", e);
		} finally {
			this.closeResource(jedis);
		}
	}

	@Override
	public void hset(String key, String field, String value) {
		verify(key);
		Jedis jedis = getResource();
		try {

			jedis.hset(key, field, value);

		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("hset filed,", e);
		} finally {
			this.closeResource(jedis);
		}
	}

	@Override
	public void expire(String key, long time) {
		verify(key);
		Jedis jedis = getResource();
		try {

			jedis.expire(key, (int) time / 1000);

		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("hset filed,", e);
		} finally {
			this.closeResource(jedis);
		}
	}

	@Override
	public <T> T hmget(String key, Class<T> cls, String... filed) {
		verify(key);
		Jedis jedis = getResource();
		try {
			LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
			String fileds[] = new String[filed.length];
			for (int i = 0; i < filed.length; i++) {
				fileds[i] = filed[i];
			}

			List<String> list = jedis.hmget(key, fileds);
			if (null == list) {
				throw new CacheRuntimeException("value empty,result=" + list);
			}
			for (int i = 0; i < filed.length; i++) {
				values.put(filed[i], list.get(i));
			}
			return mapToObject(values, cls);
		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("set filed,", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;
	}

	@Override
	public <T> T hgetAll(String key, Class<T> cls) {
		verify(key);
		Jedis jedis = getResource();
		try {
			LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
			Field[] objFields = cls.getDeclaredFields();

			String fileds[] = new String[objFields.length];
			for (int i = 0; i < objFields.length; i++) {
				fileds[i] = objFields[i].getName();
			}

			List<String> list = jedis.hmget(key, fileds);
			if (null == list) {
				throw new CacheRuntimeException("value empty,result=" + list);
			}
			for (int i = 0; i < objFields.length; i++) {
				values.put(objFields[i].getName(), list.get(i));
			}
			return mapToObject(values, cls);
		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("set filed,", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		verify(key);
		Jedis jedis = getResource();
		try {

			Map<String, String> values = jedis.hgetAll(key);
			return values;
		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("set filed,", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;
	}

	@Override
	public void hmsetOfMap(String key, Map<String, String> values) {
		verify(key);
		Jedis jedis = getResource();
		String result = "";
		try {
			result = jedis.hmset(key, values);

		} catch (Exception e) {
			logger.error("error=", e);
			// throw new CacheRuntimeException("hset filed,", e);
		} finally {
			this.closeResource(jedis);
		}
	}

	@Override
	public void hmsetOfMap(byte[] key, Map<byte[], byte[]> values) {
		verify(key);
		Jedis jedis = getResource();
		String result = "";
		try {
			result = jedis.hmset(key, values);
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
	}

	@Override
	public Object getObject(String key) {
		Object value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(SafeEncoder.encode(key))) {
				value = getToObject(jedis.get(SafeEncoder.encode(key)));
			}

			return value;
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;
	}

	@Override
	public Object getObject(byte[] key) {
		Object value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			value = getToObject(jedis.get(key));
			return value;
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;
	}

	@Override
	public void setObject(String key, Object value, long timeout) {
		verify(key);
		Jedis jedis = null;
		String result = "";
		try {
			jedis = getResource();
			result = jedis.set(SafeEncoder.encode(key), getToBytes(value));
			if (!"OK".equals(result)) {
				throw new CacheRuntimeException("value empty,result=" + value);
			}

			if (timeout != 0) {
				jedis.expire(key, (int) (timeout / 1000));
			}
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
	}

	@Override
	public long delLikeKey(String keyPrefix) {
		verify(keyPrefix);
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			Set<byte[]> keys = this.getKeysByte(keyPrefix);
			if (null != keys) {
				for (byte[] bs : keys) {
					result = jedis.del(bs);
				}
			}
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}

		return result;
	}

	@Override
	public boolean hexists(String key, String filed) {
		verify(key);
		verify(filed);

		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.hexists(key, filed);
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return false;
	}

	/**
	 * 获取Map缓存
	 *
	 * @param key
	 *            键
	 * @return 值
	 * @throws Exception
	 */
	public Map<String, Object> getObjectMap(String key) {
		verify(key);
		Map<String, Object> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			value = Maps.newHashMap();
			Map<byte[], byte[]> map = jedis.hgetAll(getBytesKey(key));
			for (Map.Entry<byte[], byte[]> e : map.entrySet()) {
				value.put(StringUtils.toString(e.getKey()), getToObject(e.getValue()));
			}
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return value;
	}

	/**
	 * 获取Map缓存
	 *
	 * @param key
	 *            键
	 * @return 值
	 * @throws Exception
	 */
	public Map<byte[], byte[]> getObjectMap(byte[] mapKey) {
		verify(mapKey);
		Map<byte[], byte[]> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			value = jedis.hgetAll(mapKey);
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return value;
	}

	/**
	 * 向Map缓存中添加值
	 *
	 * @param keyPrefix
	 *            key前缀 做模糊删除用
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return String
	 */
	public void setMapObjectPut(String key, Map<String, Object> value, long timeout) {
		verify(key);
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			Map<byte[], byte[]> map = Maps.newHashMap();
			for (Map.Entry<String, Object> e : value.entrySet()) {
				map.put(getBytesKey(e.getKey()), getToBytes(e.getValue()));
			}
			result = jedis.hmset(getBytesKey(key), (Map<byte[], byte[]>) map);
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
	}

	public boolean hdel(String key, String filed) {
		verify(key);
		verify(filed);
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hdel(getBytesKey(key), getBytesKey(filed));
			return true;
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return false;
	}

	public boolean hdel(byte[] mapKey, byte[] field) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hdel(mapKey, field);
			return true;
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return false;
	}

	private HashMap<String, String> objectToMap(Object value) throws IllegalArgumentException, IllegalAccessException {
		HashMap<String, String> keyMap = new HashMap<String, String>();
		Field[] fields = value.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true); // 设置些属性是可以访问的
			Object val = field.get(value);// 得到此属性的值
			if (null == val) {
				continue;
			}
			String type = field.getType().toString();// 得到此属性的类型
			if (type.endsWith("String")) {
				keyMap.put(field.getName(), val.toString());
			} else if (type.endsWith("int") || type.endsWith("Integer")) {
				keyMap.put(field.getName(), val.toString());
			} else if (type.endsWith("long") || type.endsWith("Long")) {
				keyMap.put(field.getName(), val.toString());
			} else if (type.endsWith("short") || type.endsWith("Short")) {
				keyMap.put(field.getName(), val.toString());
			} else if (type.endsWith("byte") || type.endsWith("Byte")) {
				keyMap.put(field.getName(), val.toString());
			} else if (type.endsWith("boolean") || type.endsWith("Boolean")) {
				keyMap.put(field.getName(), val.toString());
			} else {
				logger.error("field type not supported,type=" + field);
				throw new CacheRuntimeException("field type not supported,type=" + field);
			}
		}
		return keyMap;
	}

	private <T> T mapToObject(Map<String, String> values, Class<T> cls) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		Set<String> keySet = values.keySet();

		T object = cls.newInstance();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true); // 设置些属性是可以访问的
			String type = field.getType().toString();// 得到此属性的类型

			for (String fieldName : keySet) {
				String fieldValue = values.get(fieldName);
				if (fieldValue.equals(field.getName())) {
					if (type.endsWith("String")) {
						field.set(object, fieldValue);
					} else if (type.endsWith("int") || type.endsWith("Integer")) {
						field.set(object, Integer.parseInt(fieldValue));
					} else if (type.endsWith("long") || type.endsWith("Long")) {
						field.set(object, Long.parseLong(fieldValue));
					} else if (type.endsWith("short") || type.endsWith("Short")) {
						field.set(object, Short.parseShort(fieldValue));
					} else if (type.endsWith("byte") || type.endsWith("Byte")) {
						field.set(object, Byte.parseByte(fieldValue));
					} else if (type.endsWith("boolean") || type.endsWith("Boolean")) {
						field.set(object, Boolean.parseBoolean(fieldValue));
					} else {
						logger.error("field type not supported,type=" + field);
						throw new CacheRuntimeException("field type not supported,type=" + field);
					}
				}
			}
		}
		return object;
	}

	/**
	 * Object转换byte[]类型
	 *
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public byte[] getToBytes(Object object) throws Exception {
		return ObjectUtils.serialize(object);
	}

	/**
	 * byte[]型转换Object
	 *
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public Object getToObject(byte[] bytes) throws Exception {
		return ObjectUtils.unserialize(bytes);
	}

	/**
	 * 获取byte[]类型Key
	 *
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public byte[] getBytesKey(Object object) throws Exception {
		if (object instanceof String) {
			return StringUtils.getBytes((String) object);
		} else {
			return ObjectUtils.serialize(object);
		}
	}

	@Override
	public boolean sadd(String key, String member) {
		verify(key);
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.sadd(key, member);
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return false;
	}

	@Override
	public Set<String> smembers(String key) {
		verify(key);
		Jedis jedis = null;
		Set<String> list = null;
		try {
			jedis = getResource();
			list = jedis.smembers(key);
			return list;
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return null;
	}

	@Override
	public long srem(String key, String[] member) {
		verify(key);
		Jedis jedis = null;
		try {
			jedis = getResource();
			long res = jedis.srem(key, member);
			return res;
		} catch (Exception e) {
			logger.error("error=", e);
		} finally {
			this.closeResource(jedis);
		}
		return 0;
	}
}
