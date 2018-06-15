package com.ws.framework.common.logger.filter;
/**
 * Created by Administrator on 2018/6/14.
 */

import com.ws.framework.common.logger.TraceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * servlet filter,http接口加上traceId
 * @author wangsi
 * @create 2018-06-14 15:55
 **/
public class ServletTraceFilter implements Filter{

    protected static final Logger logger = LoggerFactory.getLogger(ServletTraceFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String traceId = httpRequest.getHeader("trace_id");
        logger.info("servlet traceFilter traceId={}",traceId);
        System.out.println("servlet traceFilter traceId=" + traceId);
        try {
            if(traceId == null){
                logger.info("set traceId");
                System.out.println("set traceid");
                TraceUtils.beginTrace();
            }else {
                TraceUtils.beginTrace(traceId);
            }
            filterChain.doFilter(servletRequest,servletResponse);
        }finally {
            logger.info("traceId end");
            TraceUtils.endTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
