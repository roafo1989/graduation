package com.example.grad1.controller.base;

import com.example.grad1.domain.Restaurant;
import com.example.grad1.service.restaurant.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.List;

import static com.example.grad1.util.ValidationUtil.assureIdConsistent;
import static com.example.grad1.util.ValidationUtil.checkNew;

public class AbstractRestaurantController {

    @Autowired
    private RestaurantService service;

    public List<Restaurant> getAllWithLunches(LocalDate date) {
        return service.getAllWithLunches(date);
    }

    public Restaurant getById(int id) {
        return service.get(id);
    }

    public List<Restaurant> getAll() {
        return service.getAll();
    }

    public void delete(int id) {
        service.delete(id);
    }

    public Restaurant update(Restaurant restaurant, int id) {
        assureIdConsistent(restaurant, id);
        return service.update(restaurant);
    }

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        return service.create(restaurant);
    }

}
