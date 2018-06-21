package com.ws.framework.common.utils;
/**
 * Created by Administrator on 2018/6/21.
 */

/**
 * @author wangsi
 * @create 2018-06-21 17:48
 **/
public class CompareUtil {
    /**
     * 是否相等
     * @param inta
     * @param intb
     * @return
     */
    public static boolean equals(Number inta, Number intb) {
        if (inta == null && intb == null) {
            return true;
        }
        if (inta == null || intb == null) {
            return false;
        }
        return inta.equals(intb);
    }

    /**
     * 是否相等
     * @param stra
     * @param strb
     * @return
     */
    public static boolean equals(String stra, String strb) {
        if (stra == null && strb == null) {
            return true;
        }
        if (stra == null || strb == null) {
            return false;
        }
        return stra.equals(strb);
    }
}
