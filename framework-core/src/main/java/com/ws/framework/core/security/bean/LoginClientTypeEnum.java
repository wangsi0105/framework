package com.ws.framework.core.security.bean;
/**
 * Created by Administrator on 2018/6/21.
 */

/**
 * @author wangsi
 * @create 2018-06-21 17:41
 **/
public enum  LoginClientTypeEnum {

    /**移动设备 */
    MOBILE_APP(1),

    /**微信移动端H5*/
    MOBILE_WEB(2),

    /**PC客户端*/
    PC_APP(3),

    /**PC浏览器*/
    PC_WEB(4);

    private int value;

    LoginClientTypeEnum(int value)
    {
        this.value=value;
    }

    public int getValue()
    {
        return value;
    }
}
