package com.company.project.aop;

import com.alibaba.fastjson.JSON;
import com.company.project.utils.Util;
import com.google.common.base.Stopwatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Aspect
@Component
public class LogControllerAspect {


    private static final Logger log = LoggerFactory
            .getLogger(LogControllerAspect.class);

    @Value("${sms.ipwhitelist:''}")
    private String ipwhiteList;


    @Autowired(required = false)
    private HttpServletRequest request;

    @Around("execution(* com.company.project.web.controller..*(..))")
    public Object requestPointCut(ProceedingJoinPoint pjp)
            throws Throwable {
        String requestIp = Util.getRealIpAddr(request);
        logRequestLog(requestIp);
        Stopwatch watch = Stopwatch.createStarted();
        Object result = pjp.proceed();
        watch.stop();
        log.info("[{}:{}],response <= {},time:{}", requestIp, request.getServletPath(),
                result, watch.toString());
        return result;
    }


    @Around("execution(* com.company.project.web.exception..*(..)) ")
    public Object exceptionPointCut(ProceedingJoinPoint pjp)
            throws Throwable {
        Object result = pjp.proceed();
        log.error("error [{}:{}],request:{},response <= {} ", Util.getRealIpAddr(request), request.getServletPath
                (), JSON.toJSONString
                (request.getParameterMap()), result);
        return result;
    }


    private void logRequestLog(String requestIp) {
        MDC.put("traceId", UUID.randomUUID().toString());
        log.info("[{}:{}],request  => {}", requestIp, request.getServletPath(), JSON.toJSONString
                (request.getParameterMap()));
    }



}
