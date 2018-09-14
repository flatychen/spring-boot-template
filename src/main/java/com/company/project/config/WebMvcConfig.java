package com.company.project.config;

import com.company.project.web.interceptor.CROSHandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//            registry.addInterceptor()
        registry.addInterceptor(new CROSHandlerInterceptor()).addPathPatterns("/**");
    }


}
