package com.ws.framework.web.security;
/**
 * Created by Administrator on 2018/6/19.
 */

import com.alibaba.fastjson.JSON;
import com.ws.framework.common.globalconfig.ErrorCodeEnum;
import com.ws.framework.common.utils.StringUtils;
import com.ws.framework.core.domain.result.ModelResult;
import com.ws.framework.core.security.bean.HeaderBean;
import com.ws.framework.core.security.bean.UserTokenBean;
import com.ws.framework.core.security.bo.ISecurityBo;
import com.ws.framework.core.security.util.LocalContext;
import com.ws.framework.web.exception.FWSecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author wangsi
 * @create 2018-06-19 19:14
 **/
public class FWWebSecurityManager {

    private final static Logger logger = LoggerFactory.getLogger(FWWebSecurityManager.class);

    @Autowired
    private ISecurityBo iSecurityBo;

    /**
     * 不需要验证地址集合
     */
    private List<String> extUrl;

    /**
     * 超时时间
     */
    private long effectiveTime = 1296000000L;

    /**
     * 续期间隔时间 默认0.5天
     */
    private long exTime = 43200000L;

    /**
     * token cookie域
     */
    private String tokenDomain;

    /**
     * 是否抛出异常
     */
    private boolean isThrowException;

    /**
     * 是否使用正则
     */
    private boolean isReg;

    public List<String> getExtUrl() {
        return extUrl;
    }

    public void setExtUrl(List<String> extUrl) {
        this.extUrl = extUrl;
    }

    public long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public long getExTime() {
        return exTime;
    }

    public void setExTime(long exTime) {
        this.exTime = exTime;
    }

    public String getTokenDomain() {
        return tokenDomain;
    }

    public void setTokenDomain(String tokenDomain) {
        this.tokenDomain = tokenDomain;
    }

    public boolean isThrowException() {
        return isThrowException;
    }

    public void setThrowException(boolean throwException) {
        isThrowException = throwException;
    }

    public boolean isReg() {
        return isReg;
    }

    public void setReg(boolean reg) {
        isReg = reg;
    }

    /**
     * 检查是否登录,未登录则抛出异常
     * @param url
     */
    public void isLogin(String url) throws FWSecurityException{

        String token = null;
        UserTokenBean bean = null;
        HeaderBean header = LocalContext.getHeaderBean();
        if(header != null){
            token = header.getToken();
        }
        if(!StringUtils.isEmpty(token)){
            //从redis中获取tokenBean
            bean = iSecurityBo.getTokenBean(header);
            LocalContext.setUserTokenBean(bean);
            exToken(bean);
        }
        if (validateUrl(token, url)) {
            return;
        }


        if(bean==null){
            logger.error("userTokenBean is null for cache,token={}|url={}",token,url);
            throw new FWSecurityException(new ModelResult<Void>("登录失效"));
//            throw new FWSecurityException("登录失效");
        }else {
            if(bean.getLogout()!=null && bean.getLogout()){
                // TODO: 2018/6/20
                //该token已经被其它设备踢出，抛出异常提示并删除该token
                logger.error("del logout token for cache,tokenBean={}|url={}",bean,url);
                throw new FWSecurityException(new ModelResult<Void>("重复登录"));
            }
        }


    }

    private boolean validateUrl(String token, String url) {
        if(null != extUrl){
            for(String key : extUrl){
                if (key.equals(url)){
                    logger.info("matcher ext url,token={}|url={}", token, url);
                    return true;
                }
            }
        }
        return false;
    }

    private void exToken(UserTokenBean bean) {
        if(null == bean){
            return;
        }

        if(System.currentTimeMillis()-bean.getLastUpdateTime() > exTime){
            logger.info("login token exTime,token bean={}", JSON.toJSONString(bean));
            iSecurityBo.exUserToken(bean, effectiveTime);

        }
    }

    public void login(UserTokenBean bean) throws FWSecurityException{

        if (null == bean) {
            throw new FWSecurityException(new ModelResult<Void>("token不存在"));
        }
        if (0l == effectiveTime) {
            throw new FWSecurityException(new ModelResult<Void>("未设置有效时间"));
        }

        if (StringUtils.isEmpty(bean.getUserId())) {
            throw new FWSecurityException(new ModelResult<Void>("userId为空"));
        }

        //判断重复登录
        Map<String, UserTokenBean> existMap = iSecurityBo.checkRepeatLogin(bean);
        if(existMap != null){
            //直接抛出异常，提示已有客户端登录
//            throw new FWSecurityException(new ModelResult<Void>("重复登录，被踢出"));

            //不在登录放提示，在被踢出方提示，需要增加推送机制
            logger.info("forced logout, tokenBean={}",bean);
            iSecurityBo.deleteRepeatLogin(bean, true);// 删除之前登录的信息
        }
        iSecurityBo.saveTokenBean(bean, effectiveTime);
        logger.info("login success! tokenBean={}", bean);
    }

    public boolean logout() {
        if(null != LocalContext.getUserTokenBean()){
            iSecurityBo.deleteTokenBean(LocalContext.getUserTokenBean());
        }
        return true;
    }
}
