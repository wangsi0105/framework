package com.ws.framework.web.exception;
/**
 * Created by Administrator on 2018/6/19.
 */

import com.ws.framework.core.domain.result.ModelResult;

/**
 * @author wangsi
 * @create 2018-06-19 18:14
 **/
public class FWSecurityException extends Exception {

    private static final long serialVersionUID = -6522842455013655710L;

    private ModelResult<Void> errorMode;

    public FWSecurityException(ModelResult<Void> errorMode){
        super(errorMode.toString());
        this.errorMode = errorMode;
    }

    public FWSecurityException(String message) {
        super(message);
    }

    public ModelResult<Void> getErrorMode()
    {
        return errorMode;
    }

    public void setErrorMode(ModelResult<Void> errorMode)
    {
        this.errorMode = errorMode;
    }

    @Override
    public String toString()
    {
        return "SecurityException [errorMode=" + errorMode + "]";
    }

}
