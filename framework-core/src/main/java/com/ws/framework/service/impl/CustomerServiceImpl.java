package com.ws.framework.service.impl;
/**
 * Created by Administrator on 2018/5/21.
 */

import com.alibaba.fastjson.JSON;
import com.ws.framework.core.mapper.CustomerMapper;
import com.ws.framework.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wangsi
 * @create 2018-05-21 17:49
 **/
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Map<String,Object> queryCustomerById(String id) {


        Map<String,Object> contact = customerMapper.queryCustomerDetail("1");
        System.out.println(JSON.toJSONString(contact));


        return contact;
    }
}
