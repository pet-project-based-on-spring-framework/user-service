package com.trl.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        createSpringApplication().run(args);
    }

    public static SpringApplication createSpringApplication() {
        return new SpringApplication(UserServiceApplication.class);
    }

}
