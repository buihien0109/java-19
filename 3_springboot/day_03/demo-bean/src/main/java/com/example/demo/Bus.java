package com.example.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("bus")
public class Bus implements Vehicle {

    @Override
    public void move() {
        System.out.println("Bus");
    }
    
}
