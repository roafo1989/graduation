package com.example.grad1.service.voice;

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
class VoiceServiceImplTest {

    @Autowired
    private VoiceService service;

    @Test
    void create() {
    }

    @Test
    void getByUserId() {
    }

    @Test
    void getAllByUserId() {
    }

    @Test
    void getByRestaurantIdAndDate() {
    }

    @Test
    void getByRestaurantBetweenDates() {
    }

    @Test
    void getByRestaurantBetweenDates1() {
    }

    @Test
    void getByRestaurantId() {
    }

    @Test
    void getAllByRestaurantIdAndDate() {
    }

    @Test
    void getRatingByDate() {
    }

    @Test
    void getAllByDate() {
    }
}