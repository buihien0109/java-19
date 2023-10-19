package com.example.demo.model.request;

import lombok.Getter;

@Getter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
}
