package com.ws.framework.common.utils;

/**
 * 枚举实现单例，可以防止反射攻击，因为枚举类实际是抽象类，不可被实例化
 * 可以防止反序列化生成多个对象，序列化时仅仅将枚举对象的name属性输出到结果中，反序列化时通过该名称查找对应的枚举类，因此
 * 可以保证和之前被序列化的对象相同
 * Created by Administrator on 2018/6/22.
 */
public enum Singleton {

    INSTANCE;

    private DBConnection dbConnection = null ;

    private Singleton(){

        dbConnection = new DBConnection();
    }

    public DBConnection getDbConnection(){
        return dbConnection;
    }






}
