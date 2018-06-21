package com.ws.framework.web.security;
/**
 * Created by Administrator on 2018/6/19.
 */

import com.ws.framework.common.utils.StringUtils;
import com.ws.framework.core.security.bean.HeaderBean;
import com.ws.framework.core.security.bean.UserTokenBean;
import com.ws.framework.core.security.util.LocalContext;
import com.ws.framework.core.security.util.SecurityConstants;
import com.ws.framework.core.security.util.SecurityUtil;
import com.ws.framework.web.exception.FWSecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录管理
 * @author wangsi
 * @create 2018-06-19 16:49
 **/
@Component
public class WebLoginManager {

    private static final Logger logger = LoggerFactory.getLogger(WebLoginManager.class);


    @Autowired
    private FWWebSecurityManager fwWebSecurityManager;

    /**
     * 检查是否登录，可以根据配置过滤一些url的检测
     * @param url
     * @throws FWSecurityException
     */
    public void isLogin(String url) throws FWSecurityException{
        fwWebSecurityManager.isLogin(url);
    }


    /**
     * 登录，保存登录信息对象,app端登录在请求头中设置osUUID
     * @param request
     * @param response
     * @param userId
     */
    public void login(HttpServletRequest request, HttpServletResponse response,String userId) throws FWSecurityException{

        UserTokenBean userToken = new UserTokenBean();
        userToken.setToken(SecurityUtil.generateToken());
        userToken.setUserId(userId);
        userToken.setLastUpdateTime(System.currentTimeMillis());

        //获取请求头信息，由拦截器设置
        HeaderBean headerBean = LocalContext.getHeaderBean();
        //设置设备唯一标识等
        userToken.setOsUUID(headerBean.getOsUUID());
        userToken.setcType(headerBean.getcType());

        login(response,userToken);


    }

    //完成登录，保存cookie操作,设置localContext
    private void login(HttpServletResponse response, UserTokenBean userToken) throws FWSecurityException{

        fwWebSecurityManager.login(userToken);

        saveCookies(response,userToken);
        LocalContext.setUserTokenBean(userToken);

    }


    private void saveCookies(HttpServletResponse response, UserTokenBean userToken) {

        Cookie cookieToken = new Cookie("token", userToken.getToken());

        cookieToken.setPath("/");
        cookieToken.setHttpOnly(true);
        response.addCookie(cookieToken);

        // TODO: 2018/6/19 设置设备唯一标识等
    }


    /**
     * 获取自定义http头
     * @param request
     * @return
     */
    public HeaderBean getHeader(HttpServletRequest request){
        HeaderBean header = new HeaderBean();

        // TODO: 2018/6/19
        Cookie tempCookie = WebUtils.getCookie(request, SecurityConstants.HttpCookie.OS_UUID);
        String osUUID = tempCookie != null ? tempCookie.getValue() : null;
        if (!StringUtils.isEmpty(osUUID)) {
            header.setOsUUID(osUUID);
        }

        // 客户端类型
        tempCookie = WebUtils.getCookie(request, SecurityConstants.HttpCookie.CLIENT_TYPE);
        String clientType = tempCookie != null ? tempCookie.getValue() : null;
        if (!StringUtils.isEmpty(clientType)) {
            header.setcType(Integer.parseInt(clientType));
        }

        tempCookie = WebUtils.getCookie(request, SecurityConstants.HttpCookie.TOKEN);
        String token = tempCookie != null ? tempCookie.getValue() : null;
        header.setToken(token);

        LocalContext.setHeaderBean(header);
        return header;
    }


    /**
     * 是否需要抛出异常
     * @return
     */
    public boolean isThrowException() {
        return fwWebSecurityManager.isThrowException();
    }

    /**
     *
     * 退出登陆
     *
     * @return true:成功 false:失败
     * @throws Exception
     * @since V1.0.0
     */
    public boolean logout()
    {
        return fwWebSecurityManager.logout();
    }

}
