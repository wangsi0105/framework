/**
 * 文件名：MasterRedisCache.java
 * 创建日期：  2016年8月31日
 * 作者：      mawei
 * 版权所有(C) 2016-2017 深圳市华康全景信息技术有限公司
 * 保留所有权利.
 */
package com.ws.framework.common.cache.redis;

import com.ws.framework.common.globalconfig.SystemConfig;
import com.ws.framework.common.utils.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

/**
 * 功能描述：
 * 
 * @author wangsi 2018年5月23日
 */
public class MasterRedisCache extends AbstractRedisCache
{
    private static MasterRedisCache masterRedisCache = null;

    protected JedisPool jedisPool = null;

    private MasterRedisCache()
    {
        init();
    }

    private void init()
    {
        try
        {
            System.out.println("--------------master redis start ...-----------------");
            // 从公共配置读取信息
            String ip = SystemConfig.getValueByKey("redis.master.address", "127.0.0.1");
            int port = Integer.valueOf(SystemConfig.getValueByKey("redis.master.port", "6379"));
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(200);
            config.setMaxIdle(10);
            config.setMaxWaitMillis(8000);
            config.setTestOnBorrow(true);
            String passwd = SystemConfig.getValueByKey("redis.master.passwd", "");
            if (StringUtils.isEmpty(passwd))
            {
                jedisPool = new JedisPool(config, ip, port, 8000);
            }
            else
            {
                jedisPool = new JedisPool(config, ip, port, 8000, passwd);
            }
            System.out.println("-------------master redis start success ip=" + ip + "|port=" + port + "|passwd="
                    + passwd + "------------------");
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取单列对象 MasterJedisUtils实例抽象描述
     * 
     * @return
     * @since V1.0.0
     */
    public static AbstractRedisCache getInstance()
    {
        if (masterRedisCache == null)
        {
            synchronized (MasterRedisCache.class)
            {
                masterRedisCache = new MasterRedisCache();
            }
        }
        return masterRedisCache;
    }

    /**
     * 获取资源
     * 
     * @return
     * @throws JedisException
     */
    protected Jedis getResource() throws JedisException
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
        }
        catch (JedisException e)
        {
            closeResource(jedis);
            logger.error("error=", e);
            throw e;
        }
        return jedis;
    }

    /**
     * 释放资源
     * 
     * @param jedis
     * @param isBroken
     */
    protected void closeResource(Jedis jedis)
    {
        if (jedis != null)
        {
            jedis.close();
            // System.out.println("---------NumActive:"+jedisPool.getNumActive()+"---------NumIdle:"+jedisPool.getNumIdle()+"---------NumWaiters:"+jedisPool.getNumWaiters());
        }
    }
}
