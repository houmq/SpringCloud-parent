package com.eruekaclient.demo.config;

import ch.qos.logback.access.servlet.TeeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Tomcat中使用logback
 */
@Configuration
public class TeeFilterConfig {

    @Bean
    public FilterRegistrationBean teeFilterRegistration() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new TeeFilter());
        filterRegistrationBean.addUrlPatterns("*");
        return filterRegistrationBean;
    }


}