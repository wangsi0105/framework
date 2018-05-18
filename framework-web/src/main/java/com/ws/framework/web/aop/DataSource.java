/*
 * 文 件 名:  DataSource.java
 * 版    权:  深圳市华康全景信息技术有限公司
 * 描    述:  数据源类型注释定义类
 * 修 改 人:  路猷
 * 修改时间:  2016年4月28日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ws.framework.web.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源类型注释定义
 * 
 * @author 路猷
 * @version [版本号, 2016年4月28日]
 * @see [定义多种数据源类型，在其他类上可以引起该注释]
 * @since [开发框架/V1.0.0]
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
	String name() default DataSource.hk_user;
	// 用户库
	public static String hk_user = "dataSource_hk_user";
	// 医院库
	public static String hk_hospital = "dataSource_hk_hospital";
	// 订单库
	public static String hk_order = "dataSource_hk_order";
	// 规则系统库
	public static String hk_rule = "dataSource_hk_rule";
	// 文献库
	public static String hk_document = "dataSource_hk_document";
	// 短信库
	public static String hk_message = "dataSource_hk_message";
	// 公共库
	public static String hk_common = "dataSource_hk_common";
	// 医客库
	public static String hk_medical = "dataSource_hk_medical";
	// 统计库
	public static String hk_statistics = "dataSource_hk_statistics";
	// IM云信库
	public static String hk_imserver = "dataSource_hk_imserver";
	// 支付订单库
	public static String hk_orderparentdefault = "dataSource_hk_orderparentdefault";
	
	// 用户库从库1
	public static String hk_user_slave1 = "dataSource_hk_user_slave1";
	// 医院库从库1
	public static String hk_hospital_slave1 = "dataSource_hk_hospital_slave1";
	// 订单库从库1
	public static String hk_order_slave1 = "dataSource_hk_order_slave1";
	// 规则系统库从库1
	public static String hk_rule_slave1 = "dataSource_hk_rule_slave1";
	// 文献库从库1
	public static String hk_document_slave1 = "dataSource_hk_document_slave1";
	// 短信库从库1
	public static String hk_message_slave1 = "dataSource_hk_message_slave1";
	// 公共库从库1
	public static String hk_common_slave1 = "dataSource_hk_common_slave1";
	// 医客库从库1
	public static String hk_medical_slave1 = "dataSource_hk_medical_slave1";
	// 统计库从库1
	public static String hk_statistics_slave1 = "dataSource_hk_statistics_slave1";
	// IM云信库从库1
	public static String hk_imserver_slave1 = "dataSource_hk_imserver_slave1";
	// 支付订单库从库1
	public static String hk_orderparentdefault_slave1 = "dataSource_hk_orderparentdefault_slave1";
	
	// 用户库从库2
	public static String hk_user_slave2 = "dataSource_hk_user_slave2";
	// 医院库从库2
	public static String hk_hospital_slave2 = "dataSource_hk_hospital_slave2";
	// 订单库从库2
	public static String hk_order_slave2 = "dataSource_hk_order_slave2";
	// 规则系统库从库2
	public static String hk_rule_slave2 = "dataSource_hk_rule_slave2";
	// 文献库从库2
	public static String hk_document_slave2 = "dataSource_hk_document_slave2";
	// 短信库从库2
	public static String hk_message_slave2 = "dataSource_hk_message_slave2";
	// 公共库从库2
	public static String hk_common_slave2 = "dataSource_hk_common_slave2";
	// 医客库从库2
	public static String hk_medical_slave2 = "dataSource_hk_medical_slave2";
	// 统计库从库2
	public static String hk_statistics_slave2 = "dataSource_hk_statistics_slave2";
	// IM云信库从库2
	public static String hk_imserver_slave2 = "dataSource_hk_imserver_slave2";
	// 支付订单库从库2
	public static String hk_orderparentdefault_slave2 = "dataSource_hk_orderparentdefault_slave2";
	
}