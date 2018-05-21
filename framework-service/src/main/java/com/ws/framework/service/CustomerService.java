package com.ws.framework.service;
/**
 * Created by Administrator on 2018/5/21.
 */

import java.util.Map;

/**
 * @author wangsi
 * @create 2018-05-21 16:38
 **/
public interface CustomerService {

    Map<String,Object> queryCustomerById(String id);

}
