package com.luv2code.aopdemo.around.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
public class TrafficFortuneService {

    public String getFortune() {
        // simulate a delay
        try {
            TimeUnit.SECONDS.sleep(5);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // return a fortune
        return "Expect heavy traffic this morning";
    }
}
