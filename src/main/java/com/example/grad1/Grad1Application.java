package com.example.grad1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Grad1Application {

    public static void main(String[] args) {
        SpringApplication.run(Grad1Application.class, args);
    }

}
