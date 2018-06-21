package com.ws.framework.common.globalconfig;
/**
 * Created by Administrator on 2018/6/19.
 */

/**
 * 错误码定义
 * @author wangsi
 * @create 2018-06-19 18:03
 **/
public enum  ErrorCodeEnum {

    /** 成功 */
    OK(0, true),

    /** 系统错误 */
    S_100000(10000, true),

    ;

    int code;

    ErrorCodeEnum(int code, boolean isSysModule)
    {
        this.code = code;
    }

    /**
     * 获取枚举值
     * @return int
     */
    public int getValue()
    {
        return code;
    }


    /**
     * 获取错误提示信息，需要配置
     * @param code
     * @return
     */
    public static String getMessage(int code)
    {
        return "";
    }

}
