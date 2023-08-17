package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class User {
    public User() {
        System.out.println("User Created");
    }

    public void work() {
        System.out.println("User Work");
    }
}
