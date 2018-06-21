package com.ws.framework.core.security.bean;
/**
 * Created by Administrator on 2018/6/19.
 */

import java.io.Serializable;
import java.util.Map;

/**
 * @author wangsi
 * @create 2018-06-19 16:03
 **/
public class UserTokenBean implements Serializable {

    private static final long serialVersionUID = 4196106159254872814L;
    /**
     * 唯一表示当前登录
     */
    private String token;

    /**
     * 当前登录关联用户ID
     */
    private String userId;

    /**
     * 客户端类型
     */
    private Integer cType;

    /**
     * 设备ID
     */
    private String osUUID;

    /**
     * 最后修改时间
     */
    private long lastUpdateTime;

    /**
     * 由于其它设备登录同一账户，导致该token被踢出时，这里为true。否则为false
     */
    private Boolean logout;

    /**
     * 扩展数据
     */
    private Map<String,String> attr;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getcType() {
        return cType;
    }

    public void setcType(Integer cType) {
        this.cType = cType;
    }

    public String getOsUUID() {
        return osUUID;
    }

    public void setOsUUID(String osUUID) {
        this.osUUID = osUUID;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Boolean getLogout() {
        return logout;
    }

    public void setLogout(Boolean logout) {
        this.logout = logout;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }
}
