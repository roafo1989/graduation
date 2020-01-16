package com.example.grad1.service.lunch;

import com.example.grad1.Grad1Application;
import com.example.grad1.domain.Lunch;
import com.example.grad1.to.lunchTo.LunchTo;
import com.example.grad1.to.lunchTo.LunchUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.example.grad1.LunchTestData.*;
import static com.example.grad1.RestaurantTestData.RESTAURANT1;
import static com.example.grad1.RestaurantTestData.RESTAURANT1_ID;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Grad1Application.class)
class LunchServiceImplTest {
    @Autowired
    private LunchService service;

    @Test
    void create() {
        Lunch newLunch = getCreated();
        Lunch created = service.create(newLunch, RESTAURANT1_ID);
        newLunch.setRestaurant(RESTAURANT1);
        Assert.assertEquals(newLunch,created);
    }
    @Test
    void update() {
        Lunch lunch = service.update(getUpdated(),RESTAURANT1_ID);
        Assert.assertEquals(lunch,service.get(LUNCH1_ID,RESTAURANT1_ID));
    }
    @Test
    void get() {
        Lunch lunch = service.get(LUNCH1_ID,RESTAURANT1_ID);
        Assert.assertEquals(LUNCH1,lunch);
    }
    @Test
    void getAll() {
        List<Lunch> lunches = service.getAll(RESTAURANT1_ID);
        Assert.assertEquals(lunches, Arrays.asList(LUNCH1,LUNCH2,LUNCH3));
    }
    @Test
    void delete() {
        service.delete(LUNCH1_ID,RESTAURANT1_ID);
        Assert.assertEquals(service.getAll(RESTAURANT1_ID),Arrays.asList(LUNCH2,LUNCH3));
    }
}