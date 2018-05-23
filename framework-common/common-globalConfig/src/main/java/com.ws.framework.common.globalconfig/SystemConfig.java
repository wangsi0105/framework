package com.ws.framework.common.globalconfig;
/**
 * Created by Administrator on 2018/5/23.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author wangsi
 * @create 2018-05-23 14:44
 **/
public class SystemConfig {


    private static final Logger logger = LoggerFactory.getLogger(SystemConfig.class);

    private static final Properties properties = new Properties();

    static {
        try {
            System.out.println("systemConfig loading...");
            properties.load(SystemConfig.class.getClassLoader().getResourceAsStream("common-config.properties"));
        }catch (Exception e){
            logger.info("load system config error...",e);
        }
    }

    public static String getValueByKey(String key){
        return properties.getProperty(key);
    }


    public static String getValueByKey(String key,String defaultValue){
        return properties.getProperty(key,defaultValue);
    }


}
