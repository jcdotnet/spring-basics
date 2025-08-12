package com.josecarlosroman.basics.spring.boot.aop.demo.aspects;

import com.josecarlosroman.basics.spring.boot.aop.demo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Order(2)
@Component
public class LoggingAspect {

    // advices for logging

    // before advice
    // pointcut expression => run this code before the target method, where
    // the target is an object method with signature public void addAccount
    //@Before("execution(public void addAccount())")

    // AOP pointcut expressions
    // match addAccount method in our AccountDAO class
    // @Before("execution(public void com.josecarlosroman.basics.spring.boot.aop.demo.dao.addAccount())")

    // match addAccount method in ANY class
    // @Before("execution(public void addAccount())")

    // match methods staring with add in ANY class
    // @Before("execution(public void add*())")

    // match methods with ANY return type in ANY class
    // @Before("execution(* void add*())")
    // @Before("execution(void add*())") // returning type is optional

    // match methods with Account param type
    //@Before("execution(* add*(com.josecarlosroman.basics.spring.boot.aop.demo.Account))")

    // match methods with "Account" and any number of arguments
    //@Before("execution(* add*(com.josecarlosroman.basics.spring.boot.aop.demo.Account, ..))")

    // match methods with any return type and starting with add
    //@Before("execution(* add*(..)")

    @Before("com.josecarlosroman.basics.spring.boot.aop.demo.aspects.AopExpressions.forDaoPackageNoSettersNoGetters()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n===> Executing @Before advice on method");
        System.out.println("Method: " + joinPoint.getSignature());
        Object[] arguments = joinPoint.getArgs();
        for (Object argument : arguments) {
            System.out.println("Argument: " + argument);
            // if (argument instanceOf Account) {
            // // do stuff with account data
            // }
        }
    }

    // after returning advice
    @AfterReturning(
            pointcut = "execution(* com.josecarlosroman.basics.spring.boot.aop.demo.dao..AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        System.out.println("\n===>>> Executing @AfterReturning on method: " + joinPoint.getSignature().toShortString());
        System.out.println("\n===>>> Result: " + result);

        // let's post-process the data ... let's modify it :-)
        for (Account account : result) {
            account.setName(account.getName().toUpperCase());
        }
        System.out.println("\n===>>> Result: " + result);
    }

    // after throwing advice
    @AfterThrowing(
            pointcut = "execution(* com.josecarlosroman.basics.spring.boot.aop.demo.dao.AccountDAO.findAccounts(..))",
            throwing = "theException"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable theException) {

        System.out.println("\n===>>> Executing @AfterThrowing on method: " + joinPoint.getSignature().toShortString());
        System.out.println("\n===>>> The exception is: " + theException);
    }

    // after advice
    @After("execution(* com.josecarlosroman.basics.spring.boot.aop.demo.dao.AccountDAO.findAccounts(..)))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

        System.out.println("\n===>>> Executing @After (finally) on method: " + joinPoint.getSignature().toShortString());
    }

    // around advice
    @Around("execution(* com.josecarlosroman.basics.spring.boot.aop.demo.services.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("\n===>>> Executing @Around on method: " + proceedingJoinPoint.getSignature().toShortString());

        long begin = System.currentTimeMillis();

        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        }
        catch (Exception exc) {
            System.out.println(exc.getMessage());
            result = "Major accident! But no worries, your private AOP helicopter is on the way!";
        }

        long end = System.currentTimeMillis();
        System.out.println("\n=====> Duration: " + (end - begin) / 1000.0 + " seconds");

        return result;
    }


}
