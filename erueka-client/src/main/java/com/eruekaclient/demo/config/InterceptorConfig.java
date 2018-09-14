package com.eruekaclient.demo.config;


import com.eruekaclient.demo.config.handler.JsonReturnValueHandler;
import com.eruekaclient.demo.config.interceptor.AuthorizationInterceptor;
import com.eruekaclient.demo.config.resolver.CurrentUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 权限控制
 * 出参过滤
 * 入参注入
 * Created by  Hmq
 * @date on 2018/9/4.
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {



    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private JsonReturnValueHandler jsonReturnValueHandler;

    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(jsonReturnValueHandler);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }









}
