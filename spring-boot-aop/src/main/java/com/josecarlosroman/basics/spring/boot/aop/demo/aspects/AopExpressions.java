package com.josecarlosroman.basics.spring.boot.aop.demo.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

// @Aspect // in case we add advices in the future
public class AopExpressions {

    // @Pointcut allows us to reuse pointcut expressions
    // apply the pointcut expression to multiple advices
    @Pointcut("execution(* com.josecarlosroman.basics.spring.boot.aop.demo.dao.*.*(..))")
    public void forDaoPackage() {}

    @Pointcut("execution(* com.josecarlosroman.basics.spring.boot.aop.demo.dao.*.get*(..))")
    public void getters() {}

    @Pointcut("execution(* com.josecarlosroman.basics.spring.boot.aop.demo.dao.*.set*(..))")
    public void setters() {}

    @Pointcut("forDaoPackage() && !(getters() || setters())")
    public void forDaoPackageNoSettersNoGetters() {}
}
