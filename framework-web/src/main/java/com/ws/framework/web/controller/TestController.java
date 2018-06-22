package com.ws.framework.web.controller;
/**
 * Created by Administrator on 2018/4/23.
 */

import com.alibaba.fastjson.JSON;
import com.ws.framework.web.vo.TestVo;
import com.ws.framework.service.CustomerService;
import com.ws.framework.web.aop.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;

/**
 * @author wangsi
 * @create 2018-04-23 14:40
 **/
@Controller
@RequestMapping(value = "/test")
public class TestController {

    protected static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @Autowired
    private CustomerService customerService;

    @Autowired
    private Validator validator;

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    @ResponseBody
    @DataSource(name=DataSource.hk_orderparentdefault)
    public Object hello(String name,HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("application/json;charset=utf-8");

//        String traceid = "testTraceId111";
//        TraceUtils.beginTrace(traceid);
        Hello hello = new Hello();
        hello.setMsg("wangsi");
        System.out.println("king kong");
        System.out.println("get properties:" + System.getProperty("datasource.url"));

        Map<String,Object> contact = customerService.queryCustomerById("1000005817");
//        System.out.println(JSON.toJSONString(contact));

        logger.info("test controller ,hello param={}",JSON.toJSONString(contact));


        return hello;
    }

    @RequestMapping(value = "/helloBody", method = RequestMethod.POST)
    @ResponseBody
    public Object helloBody(@RequestBody TestVo testVo, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(JSON.toJSONString(testVo));

        validator.validate(testVo);

        Set<ConstraintViolation<TestVo>> set = validator.validate(testVo);
        for (ConstraintViolation<TestVo> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }



        return "helloBody";
    }


}
