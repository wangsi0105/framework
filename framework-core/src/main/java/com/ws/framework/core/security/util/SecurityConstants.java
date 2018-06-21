package com.ws.framework.core.security.util;
/**
 * Created by Administrator on 2018/6/20.
 */

/**
 * @author wangsi
 * @create 2018-06-20 10:33
 **/
public interface SecurityConstants {

    interface HttpCookie
    {
        /**
         * TOKEN
         */
        String TOKEN="token";
        /**
         * api版本号
         */
        String API_VERSION="apiVer";
        /**
         * 客户端类型
         */
        String CLIENT_TYPE="cType";
        /**
         * app 版本号
         */
        String APP_VERSION="appVer";
        /**
         * app 类型
         */
        String APP_TYPE="appType";

        /**
         * 客户端端操作系统版本
         */
        String OS_VERSION="osVer";
        /**
         * 唯一标示设备
         */
        String OS_UUID="osUUID";
        /**
         * app下载来运
         */
        String APP_SOURCE_TYPE="appSrc";

        /**
         * 应用的广告标识
         */
        String IDFA="IDFA";

    }

    /**
     * HTTP HEAD 自定义头信息 key值定义
     * 功能描述：
     */
    interface HttpHeader
    {

    }
}
