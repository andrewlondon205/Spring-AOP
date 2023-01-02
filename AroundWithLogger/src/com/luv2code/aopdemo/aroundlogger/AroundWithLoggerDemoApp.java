package com.luv2code.aopdemo.aroundlogger;

import com.luv2code.aopdemo.aroundlogger.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.logging.Logger;

public class AroundWithLoggerDemoApp {

    private static Logger myLogger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());


    public static void main(String[] args) {
        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        // get the bean from spring container
        TrafficFortuneService fortuneService = context.getBean("trafficFortuneService",TrafficFortuneService.class);

        myLogger.info("\nMain Program: AroundDemoApp");
        myLogger.info("calling getFortune");
        String data = fortuneService.getFortune();
        myLogger.info("\nMy fortune is: " + data);
        myLogger.info("Finished");

        // close the context
        context.close();
    }
}
