package com.ws.framework.core.domain.result;
/**
 * Created by Administrator on 2018/6/19.
 */

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import com.ws.framework.common.globalconfig.ErrorCodeEnum;

import java.io.Serializable;

/**
 * @author wangsi
 * @create 2018-06-19 17:58
 **/
public class ModelResult<T> implements Serializable {

    private static final long serialVersionUID = 8455325147340034608L;

    private int code;

//    @SerializedName("msg")
//    @JSONField(name="msg")
    private String errorMsg;

//    @JSONField(name="data")
//    @SerializedName(value="data")
    private T model;


    public ModelResult(){

    }

    public ModelResult(ErrorCodeEnum codeEnum) {
        super();
        this.code = codeEnum.getValue();
        this.errorMsg = codeEnum.getMessage(codeEnum.getValue());
    }

    public ModelResult(int code ,String errorMsg) {
        super();
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public ModelResult(String errorMsg){
        super();
        this.code = -1;
        this.errorMsg = errorMsg;
    }

    public void setErrorCodeAndMsg(int code ,String errorMsg)
    {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public void setErrorCodeAndMsg(ErrorCodeEnum codeEnum) {
        this.code = codeEnum.getValue();
        this.errorMsg = codeEnum.getMessage(codeEnum.getValue());
    }

    @Override
    public String toString() {
        return "ModelResult [code=" + code + ", errorMsg=" + errorMsg
                + ", model=" + model + "]";
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
