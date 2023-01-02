package com.luv2code.aopdemo.aroundlogger;

import com.luv2code.aopdemo.aroundlogger.dao.AccountDAO;
import com.luv2code.aopdemo.aroundlogger.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {
        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        // get the bean from spring container
        AccountDAO theAccoundDAO = context.getBean("accountDAO",AccountDAO.class);

        // get membership bean from spring container
        MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
        // call business method
        Account myAccount = new Account();
        myAccount.setName("John Connor");
        myAccount.setLevel("Gold");
        theAccoundDAO.addAccount(myAccount,true);
        theAccoundDAO.doWork();

        // call the accountdao getter/setter methods
        theAccoundDAO.setName("foobar");
        theAccoundDAO.setServiceCode("silver");

        String name = theAccoundDAO.getName();
        String code = theAccoundDAO.getServiceCode();

        // call the membership business method
        theMembershipDAO.addSillyMember();
        theMembershipDAO.goToSleep();

        // close the context
        context.close();
    }
}
