package com.company.project.web.aop;

import com.company.project.base.Response;
import com.company.project.utils.web.WebUtil;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Aspect
@Component
public class ControllerAspect {


    private static final Logger log = LoggerFactory
            .getLogger(ControllerAspect.class);

    @Value("${sms.ipwhitelist:''}")
    private String ipwhiteList;


    @Autowired(required = false)
    private HttpServletRequest request;

    @Around("execution(* com.company.project.web.controller..*(..))")
    public Object requestPointCut(ProceedingJoinPoint pjp)
            throws Throwable {
        MDC.put("traceId", UUID.randomUUID());
        Stopwatch watch = Stopwatch.createStarted();
        try {
            checkParamValid(pjp.getArgs());
        } catch (Exception e) {
            return wrapObject(watch, Response.fail(e.getMessage()));
        }

        return wrapObject(watch, pjp.proceed());
    }


    @Around("execution(* com.company.project.web.exception..*(..)) ")
    public Object exceptionPointCut(ProceedingJoinPoint pjp)
            throws Throwable {
        MDC.put("traceId", UUID.randomUUID());
        Map<String, String> httpParams = parseHttpParams(request.getParameterMap());
        Object result =  pjp.proceed();
        log.error("error [{}:{}],request:{},response:{} ", WebUtil.getRealIpAddr(request), request.getServletPath(),httpParams,
                result );
        return result;
    }




    private void checkParamValid(Object[] args) throws Exception {
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    List<FieldError> errors = bindingResult.getFieldErrors();
                    StringBuilder sb = new StringBuilder();
                    String _split = "";
                    for (FieldError objectError : errors) {
                        sb.append(_split).append(objectError.getField()).append(" ")
                                .append(objectError.getDefaultMessage());
                        _split = ",";
                    }
                    if (StringUtils.isNotBlank(sb.toString())) {
                        throw new Exception(sb.toString());
                    }
                }
            }
        }
    }


    private Object wrapObject(Stopwatch watch, Object result) {
        String requestIp = WebUtil.getRealIpAddr(request);
        Map<String, String> httpParams = parseHttpParams(request.getParameterMap());
        watch.stop();
        log.info("[{}:{}] time:{},request:{},response:{} ", requestIp, request.getServletPath(), watch.toString(),httpParams,
                result );

        return result;
    }


    private Map<String, String> parseHttpParams(
            Map<String, String[]> parameterMap) {
        Map<String, String> map = Maps.newHashMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue()[0]);
        }
        return map;
    }


}
