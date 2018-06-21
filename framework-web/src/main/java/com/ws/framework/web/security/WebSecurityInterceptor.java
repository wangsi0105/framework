package com.ws.framework.web.security;
/**
 * Created by Administrator on 2018/6/19.
 */

import com.alibaba.fastjson.JSON;
import com.ws.framework.core.security.bean.HeaderBean;
import com.ws.framework.core.security.util.LocalContext;
import com.ws.framework.web.exception.FWSecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangsi
 * @create 2018-06-19 19:08
 **/
public class WebSecurityInterceptor implements HandlerInterceptor {

    private final static Logger log = LoggerFactory.getLogger(WebSecurityInterceptor.class);

    @Autowired
    private WebLoginManager webLoginManager;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        try {
            LocalContext.clear();
            String url = httpServletRequest.getRequestURI();
            System.out.println("url=" + url);
            HeaderBean header=webLoginManager.getHeader(httpServletRequest);
            webLoginManager.isLogin(url);

        }catch (FWSecurityException e){
            log.info("headerInfo={}",LocalContext.getHeaderBean(),e);
            if(webLoginManager.isThrowException()){
                throw e;
            }
            httpServletResponse.getWriter().print(JSON.toJSONString(e.getErrorMode()));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        LocalContext.clear();
    }
}
