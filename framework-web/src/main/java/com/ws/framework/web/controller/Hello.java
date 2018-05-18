package com.ws.framework.web.controller;
/**
 * Created by Administrator on 2018/4/23.
 */

import java.io.Serializable;

/**
 * @author wangsi
 * @create 2018-04-23 14:46
 **/
public class Hello implements Serializable{

    private String msg ;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
