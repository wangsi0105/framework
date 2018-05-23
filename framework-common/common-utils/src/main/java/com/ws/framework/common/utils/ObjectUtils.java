/**
 * 文件名：ObjectUtils.java
 * 创建日期：  2016年7月15日
 * 作者：      mawei
 * 版权所有(C) 2016-2017 深圳市华康全景信息技术有限公司
 * 保留所有权利.
 */
package com.ws.framework.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

/**
 * 功能描述：
 * @author wangsi 2018年5月23日
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils
{
    

    /**
     * 序列化对象
     * 
     * @param object
     * @return
     */
    public static byte[] serialize(Object object)
    {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try
        {
            if (object != null)
            {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                return baos.toByteArray();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 反序列化对象
     * 
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes)
    {
        ByteArrayInputStream bais = null;
        try
        {
            if (bytes != null && bytes.length > 0)
            {
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

