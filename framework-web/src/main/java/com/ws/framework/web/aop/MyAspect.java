package com.ws.framework.web.aop;
/**
 * Created by Administrator on 2018/4/23.
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wangsi
 * @create 2018-04-23 18:01
 **/
@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(* com.ws.framework.web.controller..*.*(..))") // expression
    private void selectAll() {}  // signature


    @Pointcut("@annotation(com.ws.framework.web.aop.DataSource)") // expression
    private void annotationTest() {}  // signature



    @Before("selectAll()")
    public void beforeAdvice(JoinPoint point){
        System.out.println("Going to setup student profile.");
        Method method = ((MethodSignature)point.getSignature()).getMethod();

        System.out.println("method name = " + method.getName());

    }

//    @Before("annotationTest()")
//    public void annotationTest(JoinPoint point){
//        Method method = ((MethodSignature)point.getSignature()).getMethod();
//        DataSource datasource = method.getAnnotation(DataSource.class);
//        System.out.println("data source = " + datasource.name());
//
//        System.out.println("method name = " + method.getName());
//
//    }


    /**
     * This is the method which I would like to execute
     * after a selected method execution.
     */
    @After("selectAll()")
    public void afterAdvice(){
        System.out.println("Student profile has been setup.");
    }
    /**
     * This is the method which I would like to execute
     * when any method returns.
     */
    @AfterReturning(pointcut = "selectAll()", returning="retVal")
    public void afterReturningAdvice(Object retVal){
        System.out.println("Returning:" + retVal.toString() );
    }
    /**
     * This is the method which I would like to execute
     * if there is an exception raised by any method.
     */
    @AfterThrowing(pointcut = "selectAll()", throwing = "ex")
    public void AfterThrowingAdvice(IllegalArgumentException ex){
        System.out.println("There has been an exception: " + ex.toString());
    }

}
