package com.impetus.onlinebookstore.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * MyAspect is a component that serves as an aspect for logging method entry.
 */
@Component
@Aspect
public class MyAspect {
    /**
     * Logger for logging aspect actions.
     */
    private Logger logger = LoggerFactory.getLogger(MyAspect.class);
    @Before("@annotation(com.impetus.onlinebookstore.logger.Loggable)")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Entering the method: {}", methodName);
    }
}
