package com.ws.framework.core.mapper;
/**
 * Created by Administrator on 2017/8/11.
 */


import com.ws.framework.core.domain.Contact;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author wangsi
 * @create 2017-08-11 15:12
 **/
@Repository
public interface CustomerMapper {

    /**
     * 查询客户信息
     *
     * @return
     */
    Contact queryCustomer();


    Map<String,Object> queryCustomerDetail(String customerNo);


}
