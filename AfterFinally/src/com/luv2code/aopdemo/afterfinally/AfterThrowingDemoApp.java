package com.luv2code.aopdemo.afterfinally;

import com.luv2code.aopdemo.afterfinally.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterThrowingDemoApp {

    public static void main(String[] args) {
        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        // get the bean from spring container
        AccountDAO theAccoundDAO = context.getBean("accountDAO",AccountDAO.class);

        // call method to find the accounts
        List<Account> theAccounts = null;

        try {
            boolean tripWire = true;
            theAccounts = theAccoundDAO.findAccounts(tripWire);
        } catch (Exception exc) {
            System.out.println("\n\n Main program ... caught exception: " + exc);
        }

        //display the accounts
        System.out.println("\n\nMain Program: com.luv2code.aopdemo.afteradvice.AfterReturningDemoApp");
        System.out.println("----");

        System.out.println(theAccounts);
        System.out.println("\n");


        // close the context
        context.close();
    }
}
