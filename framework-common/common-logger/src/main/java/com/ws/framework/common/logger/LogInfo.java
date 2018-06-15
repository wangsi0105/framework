package com.ws.framework.common.logger;
/**
 * Created by Administrator on 2018/6/14.
 */

import java.util.Date;

/**
 * @author wangsi
 * @create 2018-06-14 14:31
 **/
public class LogInfo {

    /**
     * 日志唯一ID用于跟踪处理经过代码路劲的跟踪。唯一标示请求链路
     */
    private String logId;

    /**
     * 客户端请求IP地址
     */
    private String clientIp;

    /**
     * 日志发生记录时间
     */
    private Date recordTime;

    /**
     * 用户ID 只有在用户登录方式请求时才有意义
     */
    private String userId;

    /**
     *
     */
    private String url;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
