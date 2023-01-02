package com.luv2code.aopdemo.around;

import com.luv2code.aopdemo.around.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterReturningDemoApp {

    public static void main(String[] args) {
        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        // get the bean from spring container
        AccountDAO theAccoundDAO = context.getBean("accountDAO",AccountDAO.class);

        // call method to find the accounts
        List<Account> theAccounts = theAccoundDAO.findAccounts(false);

        //display the accounts
        System.out.println("\n\nMain Program: com.luv2code.aopdemo.afteradvice.AfterReturningDemoApp");
        System.out.println("----");

  //      System.out.println(theAccounts);
        System.out.println("\n");


        // close the context
        context.close();
    }
}
