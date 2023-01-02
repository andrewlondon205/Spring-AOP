package com.luv2code.aopdemo.aroundlogger.aspect;

import com.luv2code.aopdemo.aroundlogger.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
    // add a new advice for @AfterReturning on the findAccounts method

    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Around("execution(* com.luv2code.aopdemo.aroundlogger.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        //print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        myLogger.info("\n======> Executing @Around on method: " + method);

        //get begin timestamp
        long begin = System.currentTimeMillis();
        //now, let's execute the method
        Object result = theProceedingJoinPoint.proceed();

        //get end timestamp
        long end = System.currentTimeMillis();

        //compute duration and display it
        long duration = end - begin;
        myLogger.info("\n=====> Duration: " + duration / 1000.0 + " seconds");
        return result;
    }

    @After("execution(* com.luv2code.aopdemo.aroundlogger.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        myLogger.info("\n======> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(pointcut = "execution(* com.luv2code.aopdemo.aroundlogger.dao.AccountDAO.findAccounts(..))",
                   throwing = "theExc")
    public void afterThrowingFindAccountsAdvice (JoinPoint theJoinPoint, Throwable theExc) {
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        myLogger.info("\n======> Executing @Afterthrowing on method: " + method);
        // log the exception
        myLogger.info("\n=====> The exception is: " + theExc);

    }

    @AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.aroundlogger.dao.AccountDAO.findAccounts(..))",
                    returning = "result")
    public void afterReturningFindAccountsAdvice (JoinPoint theJoinpoint, List<Account> result) {

        // print out which method we are advising on
        String method = theJoinpoint.getSignature().toShortString();
        myLogger.info("\n=====> Executing@AfterReturning on method: " + method);

        // print out the results of the method call
        myLogger.info("\n=====> result is : " + result);

        // let's post-process the data ... let us modify it

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        myLogger.info("\n=====> result is : " + result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // loop through accounts
        for (Account tempAccount : result) {
            // get uppercase version of name
            String uppername = tempAccount.getName().toUpperCase();
            // update the name on the account
            tempAccount.setName(uppername);
        }



    }

    @Before("com.luv2code.aopdemo.aroundlogger.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        myLogger.info("\n=====>>> Executing @Before advice on method");

        MethodSignature signature = (MethodSignature) theJoinPoint.getSignature();

        myLogger.info("Method: " + signature);

        //display method arguments

        // get args
        Object [] args = theJoinPoint.getArgs();
        // loop through arguments
        for (Object tempArg : args) {
            myLogger.info(tempArg.toString());

            if(tempArg instanceof Account) {
                //downcast and print com.luv2code.aopdemo.afteradvice.Account specific stuff
                Account theAccount = (Account) tempArg;
                myLogger.info("account name: " + theAccount.getName());
                myLogger.info("account level: " + theAccount.getLevel());
            }
        }
    }


}
