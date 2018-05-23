/**
 * 文件名：CacheRuntimeException.java
 * 创建日期：  2016年5月7日
 * 作者：      lizhangxiong
 * 版权所有(C) 2016-2017 深圳市华康全景信息技术有限公司
 * 保留所有权利.
 */
package com.ws.framework.common.cache;

/**
 * 功能描述：参数结构自定义异常
 * @author wangsi 2018年5月23日
 */
public class CacheRuntimeException extends RuntimeException
{
    private static final long serialVersionUID = 112594986254626483L;

    public CacheRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CacheRuntimeException(String message) {
        super(message);
    }
}
