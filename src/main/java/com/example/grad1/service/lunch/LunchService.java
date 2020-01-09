package com.example.grad1.service.lunch;

import com.example.grad1.domain.Lunch;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface LunchService {
    Lunch create(@NotNull Lunch lunch);

    void delete(int id, int restaurantId);

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    Lunch get(int id, int restaurantId);

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    List<Lunch> getAll(int restaurantId);

    void update(@NotNull Lunch lunch);
}
