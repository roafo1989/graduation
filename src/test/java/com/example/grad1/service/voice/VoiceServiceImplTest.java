package com.example.grad1.service.voice;

import com.example.grad1.Grad1Application;
import com.example.grad1.domain.Restaurant;
import com.example.grad1.domain.Voice;
import com.example.grad1.service.restaurant.RestaurantService;
import com.example.grad1.service.user.UserService;
import com.example.grad1.testData.VoiceTestData;
import com.example.grad1.to.voiceTo.VoiceTo;
import com.example.grad1.to.voiceTo.VoiceUtil;
import com.example.grad1.util.ValidationUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.example.grad1.testData.RestaurantTestData.*;
import static com.example.grad1.testData.UserTestData.*;
import static com.example.grad1.testData.VoiceTestData.*;


@Sql(scripts = "classpath:populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
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
        ValidationUtil.setDeadLineTime(LocalTime.MAX);
        Voice newVoice = VoiceTestData.getCreated();
        Voice created = voiceService.create(ADMIN_ID,RESTAURANT2_ID);
        Assert.assertEquals(VoiceUtil.asTo(newVoice),VoiceUtil.asTo(created));
        ValidationUtil.setDeadLineTime(LocalTime.of(11, 00));
    }

    @Test
    void getByUserId() {
        Voice voice = voiceService.getByUserId(USER_ID, LocalDateTime.now());
        Assert.assertEquals(VOICE1,voice);
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