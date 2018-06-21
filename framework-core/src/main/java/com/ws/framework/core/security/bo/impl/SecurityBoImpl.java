package com.ws.framework.core.security.bo.impl;
/**
 * Created by Administrator on 2018/6/20.
 */

import com.alibaba.fastjson.JSON;
import com.ws.framework.common.cache.CachedUtils;
import com.ws.framework.common.utils.CompareUtil;
import com.ws.framework.common.utils.GsonUtil;
import com.ws.framework.core.security.bean.ClientTypeEnum;
import com.ws.framework.core.security.bean.HeaderBean;
import com.ws.framework.core.security.bean.LoginClientTypeEnum;
import com.ws.framework.core.security.bean.UserTokenBean;
import com.ws.framework.core.security.bo.ISecurityBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangsi
 * @create 2018-06-20 11:20
 **/
@Component
public class SecurityBoImpl implements ISecurityBo {

    private static final Logger logger = LoggerFactory.getLogger(SecurityBoImpl.class);

    private static final String USER_TOKEN_CACHE_KEY = "utoken";

    private static final String USER_LOGIN_CACHE_KEY = "ulogin";

    @Override
    public UserTokenBean getTokenBean(HeaderBean header) {
        if(header == null || header.getToken() == null){
            logger.info("arg is null ,header={}", JSON.toJSONString(header));
        }
        UserTokenBean bean = CachedUtils.get(getTokenKey(header),UserTokenBean.class);
        if(bean == null){
            logger.info("get token is null, srcTokenBean={}",JSON.toJSONString(header));
        }
        return bean;
    }

    @Override
    public void saveTokenBean(UserTokenBean bean, long effectiveTime) {
        if (bean.getLastUpdateTime() == 0) {
            bean.setLastUpdateTime(System.currentTimeMillis());
        }
        CachedUtils.set(getTokenKey(bean),bean,effectiveTime);

        //保存登录信息(以登录设备类型、客户端类型及userId为key，同一个key只能允许一个客户端登录)
        saveUserLogin(bean,effectiveTime);
    }

    @Override
    public void exUserToken(UserTokenBean bean, long effectiveTime) {
        if(bean.getLastUpdateTime()==0){
            bean.setLastUpdateTime(System.currentTimeMillis());
        }
        CachedUtils.set(getTokenKey(bean),bean,effectiveTime);
        CachedUtils.hset(getUserLoginKey(bean), bean.getToken(), GsonUtil.toJson(bean));
        CachedUtils.expire(getUserLoginKey(bean),effectiveTime);
    }

    @Override
    public void deleteTokenBean(UserTokenBean userTokenBean) {
        deleteTokenBean(userTokenBean, false);
    }

    @Override
    public Map<String, UserTokenBean> checkRepeatLogin(UserTokenBean bean) {
        Map<String, UserTokenBean> tokens = getUserTokens(bean);
        Map<String, UserTokenBean> existMap = null;
        if(tokens!=null){
            for(String key : tokens.keySet()){
                //同设备直接删除以前的
                if(CompareUtil.equals(tokens.get(key).getOsUUID(),bean.getOsUUID())){
                    UserTokenBean tmpToken = tokens.get(key);
                    logger.info("delete repeat login,srcTokenBean={}|repeatTokenBean={}", bean, tmpToken);
                    deleteTokenBean(tmpToken);
                    continue;
                }
                if(getLoginClientTypeEnum(bean) == getLoginClientTypeEnum(tokens.get(key))){
                    //客户端类型相同
                    if(existMap == null){
                        existMap = new LinkedHashMap<>();
                    }
                    existMap.put(key,tokens.get(key));
                }
            }
        }
        return existMap;
    }

    @Override
    public void deleteRepeatLogin(UserTokenBean bean, boolean isRepeatLoginTips) {
        Map<String, UserTokenBean> tokens = checkRepeatLogin(bean);
        if(null != tokens){
            for(String key : tokens.keySet()){
                UserTokenBean tmpToken = tokens.get(key);
                logger.info("delete repeat login,srcTokenBean={}|repeatTokenBean={}", bean, tmpToken);
                deleteTokenBean(tmpToken, isRepeatLoginTips);
            }
        }
    }

    //判断客户端类型
    private LoginClientTypeEnum getLoginClientTypeEnum(UserTokenBean bean) {
        int cType = LoginClientTypeEnum.PC_WEB.getValue();
        if(null != bean.getcType()){
            cType = bean.getcType();
        }
        if(ClientTypeEnum.ANDROID.getValue()==cType || ClientTypeEnum.IOS.getValue() == cType
                ||ClientTypeEnum.FW_APP_H5.getValue()==cType){
            return LoginClientTypeEnum.MOBILE_APP;
        }
        if(ClientTypeEnum.WECHAT_MOBILE_H5.getValue() == cType){
            return LoginClientTypeEnum.MOBILE_WEB;
        }
        if(ClientTypeEnum.PC_WEB.getValue()==cType){
            return LoginClientTypeEnum.PC_WEB;
        }

        return LoginClientTypeEnum.PC_WEB;
    }

    private Map<String,UserTokenBean> getUserTokens(UserTokenBean bean) {

        Map<String,UserTokenBean> result = CachedUtils.hgetAllOfMap(getUserLoginKey(bean),UserTokenBean.class);
        return result;

    }

    public void deleteTokenBean(UserTokenBean tokenBean, boolean isRepeatLoginTips){
        if(isRepeatLoginTips){
            //需要重复登录提示，保留一段时间token，只是将token状态设置为被踢出
            long effectiveTime = 1800000L;
            tokenBean.setLogout(true);
            CachedUtils.set(getTokenKey(tokenBean), tokenBean, effectiveTime);
            CachedUtils.hdel(getUserLoginKey(tokenBean), tokenBean.getToken());
        }else {
            CachedUtils.del(getTokenKey(tokenBean));
            CachedUtils.hdel(getUserLoginKey(tokenBean),tokenBean.getToken());
        }


    }

    private void saveUserLogin(UserTokenBean bean, long effectiveTime) {
        if(bean.getLastUpdateTime() == 0){
            bean.setLastUpdateTime(System.currentTimeMillis());
        }
        String loginKey = getUserLoginKey(bean);

        Map<String,UserTokenBean> loginValue = CachedUtils.hgetAllOfMap(loginKey,UserTokenBean.class);
        if(loginValue==null){
            loginValue = new LinkedHashMap<>();
        }
        //移除token过期的登录信息
        validUserLogin(loginValue,effectiveTime);
        loginValue.put(bean.getToken(),bean);
        CachedUtils.hmsetOfMap(loginKey,convertStringMap(loginValue));
    }


    private Map<String,String> convertStringMap(Map<String, UserTokenBean> loginValue) {
        Map<String, String> resultValue = new LinkedHashMap<String, String>();
        for (String key : loginValue.keySet())
        {
            resultValue.put(key, GsonUtil.toJson(loginValue.get(key)));
        }
        return resultValue;
    }

    private void validUserLogin(Map<String, UserTokenBean> loginValue, long effectiveTime) {
    }

    private String getUserLoginKey(UserTokenBean bean) {
        StringBuilder cacheKey = new StringBuilder(USER_LOGIN_CACHE_KEY);
//        cacheKey.append(":");
//        cacheKey.append(bean.getAppType());// app类型
        cacheKey.append(":");
        cacheKey.append(bean.getUserId());// 客户端类型
        return cacheKey.toString();
    }


    private String getTokenKey(HeaderBean header) {
        StringBuilder cacheKey = new StringBuilder(USER_TOKEN_CACHE_KEY);
        cacheKey.append(":").append(header.getToken());
        return cacheKey.toString();
    }

    private String getTokenKey(UserTokenBean bean) {
        StringBuilder cacheKey = new StringBuilder(USER_TOKEN_CACHE_KEY);
        cacheKey.append(":").append(bean.getToken());
        return cacheKey.toString();
    }
}
