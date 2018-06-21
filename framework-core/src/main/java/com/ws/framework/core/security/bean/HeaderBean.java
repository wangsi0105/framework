package com.ws.framework.core.security.bean;
/**
 * Created by Administrator on 2018/6/19.
 */

/**
 * @author wangsi
 * @create 2018-06-19 16:06
 **/
public class HeaderBean {

    /**
     * api接口版本号
     */
    private Integer apiVer;

    /**
     * 客户端类型 1：Android、2 iPhone、3 微信 、4 PC Web 5、内嵌H5
     */
    private Integer cType;

    /**
     * 用户登录标示
     */
    private String token;

    /**
     * APP版本
     */
    private String appVer;

    /**
     * （如iOS9/iOS10/android）
     */
    private String osVer;

    /**
     * 当前设备的唯一标识
     */
    private String osUUID;

    /**
     * 是官网、还是其他APP应用市场
     */
    private String appSrc;

    /**
     * 应用的广告标识
     */
    private String IDFA;

    public Integer getApiVer() {
        return apiVer;
    }

    public void setApiVer(Integer apiVer) {
        this.apiVer = apiVer;
    }

    public Integer getcType() {
        return cType;
    }

    public void setcType(Integer cType) {
        this.cType = cType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public String getOsVer() {
        return osVer;
    }

    public void setOsVer(String osVer) {
        this.osVer = osVer;
    }

    public String getOsUUID() {
        return osUUID;
    }

    public void setOsUUID(String osUUID) {
        this.osUUID = osUUID;
    }

    public String getAppSrc() {
        return appSrc;
    }

    public void setAppSrc(String appSrc) {
        this.appSrc = appSrc;
    }

    public String getIDFA() {
        return IDFA;
    }

    public void setIDFA(String IDFA) {
        this.IDFA = IDFA;
    }
}
