package com.ws.framework.core.security.bo;

import com.ws.framework.core.security.bean.HeaderBean;
import com.ws.framework.core.security.bean.UserTokenBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/6/20.
 */
public interface ISecurityBo {


    /**
     * 获取登录状态信息
     * @param token
     * @return
     */
    UserTokenBean getTokenBean(HeaderBean header);

    void saveTokenBean(UserTokenBean bean, long effectiveTime);

    void exUserToken(UserTokenBean bean, long effectiveTime);

    void deleteTokenBean(UserTokenBean userTokenBean);

    Map<String,UserTokenBean> checkRepeatLogin(UserTokenBean bean);

    /**
     * 删除重复登录
     * @param bean
     * @param isRepeatLoginTips 是否需要重复登录提示，如果需要会保留一段时间的token缓存，只是将token状态置为被退出登录。
     */
    void deleteRepeatLogin(UserTokenBean bean, boolean isRepeatLoginTips);
}
