package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoManagerImageApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_user() {
        User user = new User();
        user.setName("John");
        userRepository.save(user);
    }
}
