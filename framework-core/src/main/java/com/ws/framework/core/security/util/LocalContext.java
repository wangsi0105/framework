package com.ws.framework.core.security.util;
/**
 * Created by Administrator on 2018/6/19.
 */

import com.ws.framework.core.security.bean.HeaderBean;
import com.ws.framework.core.security.bean.UserTokenBean;

/**
 * @author wangsi
 * @create 2018-06-19 16:03
 **/
public class LocalContext {

    private static ThreadLocal<UserTokenBean> loginThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<HeaderBean> headerThreadLocal = new ThreadLocal<>();


    /**
     * 获取登录信息对象
     * @return
     */
    public static UserTokenBean getUserTokenBean(){
        return loginThreadLocal.get();
    }

    /**
     * 设置用户登录信息
     * @param userTokenBean
     */
    public static void setUserTokenBean(UserTokenBean userTokenBean){
        loginThreadLocal.set(userTokenBean);
    }

    /**
     * 获取用户登录userId
     * @return
     */
    public static String getLoginUserId(){
        if(null != loginThreadLocal.get()){
            return loginThreadLocal.get().getUserId();
        }
        return null;
    }

    /**
     * 获取用户请求版本号，
     * @return 没有 返回null
     */
    public static HeaderBean getHeaderBean()
    {
        return headerThreadLocal.get();
    }

    /**
     * 设置请求版本号
     * @param apiVersion
     */
    public static void setHeaderBean(HeaderBean header)
    {
        headerThreadLocal.set(header);
    }



    /**
     * 清除本地线程变量
     */
    public static void clear()
    {
        loginThreadLocal.remove();
        headerThreadLocal.remove();
    }

}
