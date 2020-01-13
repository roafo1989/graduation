package com.example.grad1.service.lunch;

import com.example.grad1.domain.Lunch;
import com.example.grad1.to.LunchTo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface LunchService {
    Lunch create(@NotNull Lunch lunch, int restaurantId);

    void delete(int id, int restaurantId);

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    Lunch get(int id, int restId);

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    List<LunchTo> getAll(int restaurantId);

    Lunch update(@NotNull Lunch lunch, int restaurantId);
}
