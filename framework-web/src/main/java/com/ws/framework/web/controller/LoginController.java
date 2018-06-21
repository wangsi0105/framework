package com.ws.framework.web.controller;
/**
 * Created by Administrator on 2018/6/20.
 */

import com.alibaba.fastjson.JSON;
import com.ws.framework.web.aop.DataSource;
import com.ws.framework.web.exception.FWSecurityException;
import com.ws.framework.web.security.WebLoginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author wangsi
 * @create 2018-06-20 17:57
 **/
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    WebLoginManager webLoginManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response) {

        String userId = "11111";

        try {
            webLoginManager.login(request,response,userId);
        } catch (FWSecurityException e) {
            e.printStackTrace();
        }


        return userId;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Object logout(HttpServletRequest request, HttpServletResponse response) {

        return webLoginManager.logout();

    }

}
