package com.luv2code.aopdemo.afterfinally.aspect;

import com.luv2code.aopdemo.afterfinally.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
    // add a new advice for @AfterReturning on the findAccounts method

    @After("execution(* com.luv2code.aopdemo.afterfinally.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(pointcut = "execution(* com.luv2code.aopdemo.afterfinally.dao.AccountDAO.findAccounts(..))",
                   throwing = "theExc")
    public void afterThrowingFindAccountsAdvice (JoinPoint theJoinPoint, Throwable theExc) {
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======> Executing @Afterthrowing on method: " + method);
        // log the exception
        System.out.println("\n=====> The exception is: " + theExc);

    }

    @AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.afterfinally.dao.AccountDAO.findAccounts(..))",
                    returning = "result")
    public void afterReturningFindAccountsAdvice (JoinPoint theJoinpoint, List<Account> result) {

        // print out which method we are advising on
        String method = theJoinpoint.getSignature().toShortString();
        System.out.println("\n=====> Executing@AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n=====> result is : " + result);

        // let's post-process the data ... let us modify it

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n=====> result is : " + result);

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

    @Before("com.luv2code.aopdemo.afterfinally.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n=====>>> Executing @Before advice on method");

        MethodSignature signature = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method: " + signature);

        //display method arguments

        // get args
        Object [] args = theJoinPoint.getArgs();
        // loop through arguments
        for (Object tempArg : args) {
            System.out.println(tempArg);

            if(tempArg instanceof Account) {
                //downcast and print com.luv2code.aopdemo.afteradvice.Account specific stuff
                Account theAccount = (Account) tempArg;
                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }
    }




}
