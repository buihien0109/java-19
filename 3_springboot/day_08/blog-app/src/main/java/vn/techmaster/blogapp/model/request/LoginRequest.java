package vn.techmaster.blogapp.model.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
