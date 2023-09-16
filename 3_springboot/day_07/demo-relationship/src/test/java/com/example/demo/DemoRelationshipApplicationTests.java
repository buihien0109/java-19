package com.example.demo;

import com.example.demo.entity.Card;
import com.example.demo.entity.User;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoRelationshipApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CardRepository cardRepository;

    @Test
    void save_user_card() {
        Faker faker = new Faker();
        for (int i = 0; i < 10 ; i++) {
            Card card = new Card();
            card.setCode(faker.code().isbn10());
            cardRepository.save(card);

            User user = new User();
            user.setName(faker.name().fullName());
            user.setCard(card);
            userRepository.save(user);
        }
    }

    @Test
    void save_user_card_2() {
        Faker faker = new Faker();
        for (int i = 0; i < 5 ; i++) {
            User user = new User();
            user.setName(faker.name().fullName());
            user.setCard(new Card(null, faker.code().isbn10()));
            userRepository.save(user);
        }
    }

    @Test
    void get_user_by_id() {
        User user = userRepository.findById(1).get();
        System.out.println(user.getName());
        System.out.println(user.getCard().getCode());
    }

    @Test
    void delete_user() {
        userRepository.deleteById(2);
    }
}
