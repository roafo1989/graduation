package com.example.grad1.service.restaurant;

import com.example.grad1.Grad1Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Grad1Application.class)
class RestaurantServiceImplTest {
    @Autowired
    private RestaurantService restaurantService;
    @Test
    void getAllWithLunches() {
    }

    @Test
    void getAll() {
    }

    @Test
    void get() {
    }

    @Test
    void delete() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }
}