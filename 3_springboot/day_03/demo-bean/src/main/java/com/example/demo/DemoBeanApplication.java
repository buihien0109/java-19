package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class DemoBeanApplication implements CommandLineRunner {
    @Autowired
    private User user;

    @Autowired
    private Random rd;

//    private User user;
//    public DemoBeanApplication(User user) {
//        this.user = user;
//    }

//    private User user;
//    @Autowired
//    public void setUser(User user) {
//        this.user = user;
//    }

    public static void main(String[] args) {
        SpringApplication.run(DemoBeanApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        user.work();
        System.out.println("Random Number = " + rd.nextInt(1000));
    }
}

