package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_user() {
        User user = new User();
        user.setUsername("admin");
        user.setGender(User.Gender.FEMALE);
        user.setStatus(User.Status.ACTIVE);
        userRepository.save(user);

        User user1 = new User();
        user1.setUsername("admin1");
        user1.setGender(User.Gender.MALE);
        user1.setStatus(User.Status.UNACTIVE);
        userRepository.save(user1);
    }
}
