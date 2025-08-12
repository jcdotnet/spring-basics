package com.josecarlosroman.basics.spring.boot.aop.demo.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(3)
@Component
public class ApiAnalytics {

    @Before("com.josecarlosroman.basics.spring.boot.aop.demo.aspects.AopExpressions.forDaoPackageNoSettersNoGetters()")
    public void performApiAnalytics(){
        System.out.println("\n===> Performing API analytics");
    }
}
