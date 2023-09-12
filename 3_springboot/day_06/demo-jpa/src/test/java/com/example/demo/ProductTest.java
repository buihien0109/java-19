package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void save_products() {
        Faker faker = new Faker();
        Random rd = new Random();
        List<String> brands = List.of("A", "B", "C", "D", "E"); // Danh sách thương hiệu
        LocalDateTime fromDate = LocalDateTime.of(2021, 1, 1, 0, 0);
        LocalDateTime toDate = LocalDateTime.now();
        for (int i = 0; i < 30; i++) {
            Product product = new Product(
                    null,
                    faker.funnyName().name(),
                    faker.number().numberBetween(1000, 9000),
                    brands.get(rd.nextInt(brands.size())),
                    generateRandomDateTime(fromDate, toDate),
                    faker.number().numberBetween(10, 100)
            );
            productRepository.save(product);
        }
    }

    public LocalDateTime generateRandomDateTime(LocalDateTime fromDate, LocalDateTime toDate) {
        Random random = new Random();

        // Convert LocalDateTime to Epoch milliseconds
        long fromMillis = fromDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long toMillis = toDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // Generate a random Epoch milliseconds value between fromMillis and toMillis
        long randomMillis = fromMillis + (long) (random.nextDouble() * (toMillis - fromMillis));

        // Convert the random milliseconds value back to LocalDateTime
        return LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(randomMillis), ZoneId.systemDefault());
    }
}
