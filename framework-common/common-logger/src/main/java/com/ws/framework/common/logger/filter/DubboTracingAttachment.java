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
 * @create 2018-06-14 17:07
 **/
//@Activate(
//        group = {"consumer"}
//)
public class DubboTracingAttachment implements Filter {

    protected static final Logger logger = LoggerFactory.getLogger(DubboTracingAttachment.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String trace_id = TraceUtils.getTraceId();
        logger.info("dubboTracingAttachment trace_id={}",trace_id);
        System.out.println("dubboTracingAttachment traceId=" + trace_id);
        if(trace_id != null){
            RpcContext.getContext().setAttachment("trace_id",trace_id);
        }
        Result result = invoker.invoke(invocation);
        return result;
    }
}
