package com.ws.framework.common.logger.filter;
/**
 * Created by Administrator on 2018/6/14.
 */

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.ws.framework.common.logger.TraceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangsi
 * @create 2018-06-14 17:14
 **/
//@Activate(
//        group = {"provider"}
//)
public class DubboTracingDetachment implements Filter{

    protected static final Logger logger = LoggerFactory.getLogger(DubboTracingDetachment.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String trace_id = RpcContext.getContext().getAttachment("trace_id");
        logger.info("dubboTracingDetachment trace_id={}",trace_id);
        if(trace_id != null){
            TraceUtils.beginTrace(trace_id);
        }
        Result result = null;
        try {
            result = invoker.invoke(invocation);
        } finally {
            TraceUtils.endTrace();
        }
        return result;
    }
}
