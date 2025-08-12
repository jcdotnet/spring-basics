package com.josecarlosroman.basics.spring.boot.aop.demo.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class CloudLogAsyncAspect {

    @Before("com.josecarlosroman.basics.spring.boot.aop.demo.aspects.AopExpressions.forDaoPackageNoSettersNoGetters()")
    public void logToCloud(){
        System.out.println("\n===> Logging to cloud async");
    }
}
