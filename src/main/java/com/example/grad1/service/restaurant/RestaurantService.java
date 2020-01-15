package com.example.grad1.service.restaurant;


import com.example.grad1.domain.Restaurant;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {
    Restaurant create(@NotNull Restaurant restaurant);

    Restaurant update(@NotNull Restaurant restaurant);

    void delete(int id);

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    Restaurant get(int id);

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    List<Restaurant> getAllWithLunches(LocalDate date);

    List<Restaurant> getAll();

//    List<Restaurant> getAllForId(LocalDate date, int id);

}
