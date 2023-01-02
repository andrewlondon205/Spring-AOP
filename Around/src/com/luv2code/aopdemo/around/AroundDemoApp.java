package com.luv2code.aopdemo.around;


import com.luv2code.aopdemo.around.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AroundDemoApp {

    public static void main(String[] args) {
        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        // get the bean from spring container
        TrafficFortuneService fortuneService = context.getBean("trafficFortuneService",TrafficFortuneService.class);

        System.out.println("\nMain Program: AroundDemoApp");
        System.out.println("calling getFortune");
        String data = fortuneService.getFortune();
        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");

        // close the context
        context.close();
    }
}
