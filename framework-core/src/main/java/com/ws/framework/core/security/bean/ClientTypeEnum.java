package com.ws.framework.core.security.bean;
/**
 * Created by Administrator on 2018/6/19.
 */

/**
 * @author wangsi
 * @create 2018-06-19 16:05
 **/
public enum  ClientTypeEnum {

    /**andriod 设备*/
    ANDROID(1),
    /**IOS设备*/
    IOS(2),
    /**微信移动端H5*/
    WECHAT_MOBILE_H5(3),
    /**PC浏览器*/
    PC_WEB(4),
    /**公司内部产品内嵌H5*/
    FW_APP_H5(5);

    private int value;

    ClientTypeEnum(int value)
    {
        this.value=value;
    }

    public int getValue()
    {
        return value;
    }
}
