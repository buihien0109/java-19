package com.example.demothymeleafspringsecurity.controller;

import com.example.demothymeleafspringsecurity.security.IsAdmin;
import com.example.demothymeleafspringsecurity.security.IsUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    // @PreAuthorize("hasRole('ROLE_USER')")
    @IsUser
    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @IsAdmin
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
