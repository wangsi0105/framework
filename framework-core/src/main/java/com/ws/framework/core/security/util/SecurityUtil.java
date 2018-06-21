package com.ws.framework.core.security.util;
/**
 * Created by Administrator on 2018/6/19.
 */

import java.util.UUID;

/**
 * @author wangsi
 * @create 2018-06-19 16:41
 **/
public class SecurityUtil {

    /**
     * 生成Token
     * @return
     */
    public static String generateToken()
    {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
