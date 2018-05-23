package com.ws.framework.common.cache.redis;
/**
 * Created by Administrator on 2018/5/23.
 */

import com.ws.framework.common.globalconfig.SystemConfig;

/**
 * @author wangsi
 * @create 2018-05-23 18:17
 **/
public class RedisCacheFactory {

    public static boolean isExistSlave = false;

    static {
        String flag = SystemConfig.getValueByKey("redis.is.exists.slave","false");
        if("true".equals(flag)){
            isExistSlave = true;
        }
    }


    /**
     * 默认获取主库写库实例
     * @return
     */
    public static AbstractRedisCache getWriteInstance()
    {
        return MasterRedisCache.getInstance();
    }


    /**
     * 默认获取主库读库实例
     *
     * @return
     * @since V1.0.0
     */
    public static AbstractRedisCache getReadInstance()
    {
        if (isExistSlave)
        {
            return MasterRedisCache.getInstance();
        }
        else
        {
            // 不存在从库的情况下返回主库
            return MasterRedisCache.getInstance();
        }
    }
}
