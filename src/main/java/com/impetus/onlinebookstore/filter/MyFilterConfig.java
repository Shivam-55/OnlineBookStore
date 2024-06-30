package com.impetus.onlinebookstore.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration class for registering the custom filter.
 */
@Configuration
public class MyFilterConfig {
    @Bean
    public FilterRegistrationBean<MyFilter> myFilterRegistration() {
        FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*"); // URL patterns to which the filter should be applied
        registration.setName("MyFilter");
        registration.setOrder(1); // Order in which the filter should be executed
        return registration;
    }
}

