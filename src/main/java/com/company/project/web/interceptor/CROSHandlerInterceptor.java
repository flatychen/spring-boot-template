package com.company.project.web.interceptor;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类名称：CROSHandlerInterceptor.java
 *
 * @author Lubin
 * @version 1.0
 */
public class CROSHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CROSHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //解决跨域访问的问题
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true"); //是否支持cookie跨域
        return true;
    }

}
