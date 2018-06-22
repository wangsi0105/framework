package com.ws.framework.web.vo;
/**
 * Created by Administrator on 2018/6/22.
 */

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * @author wangsi
 * @create 2018-06-22 16:43
 **/
public class TestVo implements Serializable{

    private static final long serialVersionUID = 3933319473488719849L;



    @Range(min=1,max=100)
    private int age ;

    @Length(min=5, max=17)
    private String idCard ;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
