package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @GetMapping("/")
    public String getHome() {
        return "Home page";
    }

    @GetMapping("/profile")
    public String getProfile() {
        return "Profile page";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "Admin Page";
    }
}
