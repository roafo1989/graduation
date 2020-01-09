package com.example.grad1.service.restaurant;


import com.example.grad1.domain.Restaurant;
import com.example.grad1.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import static com.example.grad1.util.ValidationUtil.checkNotFoundWithId;


@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant create(@NotNull Restaurant restaurant) {
        return repository.save(restaurant);
    }
    @Override
    public void update(@NotNull Restaurant restaurant) {
        checkNotFoundWithId(repository.save(restaurant),restaurant.getId());
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

}
