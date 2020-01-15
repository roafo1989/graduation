package com.example.grad1.service.restaurant;


import com.example.grad1.domain.Restaurant;
import com.example.grad1.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
    public List<Restaurant> getAllWithLunches(LocalDate date) {
        return repository.findAllWithLunches(date);
    }

/*    @Override
    public List<Restaurant> getAllForId(LocalDate date, int id){
        return checkNotFoundWithId(repository.findAllWithLunchById(date, id),id);
    }*/

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Override
    public Restaurant get(int id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Restaurant not found by id " + id));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    public Restaurant create(@NotNull Restaurant restaurant) {
        return repository.save(restaurant);
    }
    @Override
    public Restaurant update(@NotNull Restaurant restaurant) {
        repository.findById(restaurant.getId()).orElseThrow(() -> new EntityNotFoundException("Restaurant not found by id " + restaurant.getId()));
        return checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }
}
