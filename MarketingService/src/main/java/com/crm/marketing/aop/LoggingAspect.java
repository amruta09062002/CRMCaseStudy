package com.crm.marketing.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
 
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
 
    @Before("execution(* com.crm.marketing.service.*.*(..)) || execution(* com.crm.marketing.controller.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Method {} called with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }
 
    @AfterReturning(pointcut = "execution(* com.crm.marketing.service.*.*(..))", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        logger.info("Method {} executed successfully with return: {}", joinPoint.getSignature(), result);
    }

}
