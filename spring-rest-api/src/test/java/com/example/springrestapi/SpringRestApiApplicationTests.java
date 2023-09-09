package com.example.springrestapi;

import com.example.springrestapi.entity.User;
import com.example.springrestapi.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringRestApiApplicationTests {


    @Autowired
    private UserRepository userRepository;

    @Test
    void save_users() {
        Faker faker = new Faker();

        for (int i = 0; i < 30; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().phoneNumber())
                    .address(faker.address().fullAddress())
                    .avatar(faker.internet().avatar())
                    .password(faker.internet().password())
                    .build();

            userRepository.save(user);
        }
    }
    @Test
    void contextLoads() {
    }



}
