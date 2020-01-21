package com.example.grad1.service.voice;

import com.example.grad1.Grad1Application;
import com.example.grad1.service.restaurant.RestaurantService;
import com.example.grad1.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;


@Sql(scripts = "classpath:static/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Grad1Application.class)
class VoiceServiceImplTest {

    @Autowired
    private VoiceService voiceService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;

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