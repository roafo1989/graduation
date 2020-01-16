package com.example.grad1.service.restaurant;

import com.example.grad1.Grad1Application;
import com.example.grad1.RestaurantTestData;
import com.example.grad1.domain.Restaurant;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.util.Arrays;

import static com.example.grad1.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Grad1Application.class)
class RestaurantServiceImplTest {
    @Autowired
    private RestaurantService service;

    @Test
    void getAll() {
        Assert.assertEquals(service.getAll(), Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3));
    }
    @Test
    void get() {
        Restaurant restaurant = service.get(RESTAURANT1_ID);
        Assert.assertEquals(restaurant,RESTAURANT1);
    }
    @Test
    void getNotFound() {
        assertThrows(EntityNotFoundException.class, () -> service.get(1));
    }
    @Test
    void delete() {
        service.delete(RESTAURANT1_ID);
        Assert.assertEquals(service.getAll(), Arrays.asList(RESTAURANT2, RESTAURANT3));
    }
    @Test
    void create() {
        Restaurant newRest = RestaurantTestData.getCreated();
        Restaurant created = service.create(newRest);
        Assert.assertEquals(newRest, created);
    }
    @Test
    void update() {
        Restaurant restaurant = service.update(getUpdated());
        Assert.assertEquals(restaurant, service.get(RESTAURANT1_ID));
    }
    @Test
    void updateNotFound() {
        Restaurant restaurant = new Restaurant(1, "New");
        assertThrows(EntityNotFoundException.class, () -> service.update(restaurant));
    }
}