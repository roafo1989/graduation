package com.example.grad1.service.lunch;

import com.example.grad1.domain.Lunch;
import com.example.grad1.domain.Restaurant;
import com.example.grad1.repository.LunchRepository;
import com.example.grad1.repository.RestaurantRepository;
import com.example.grad1.to.model.LunchTo;
import com.example.grad1.to.converter.LunchUtil;
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
public class LunchServiceImpl implements LunchService {
    private final LunchRepository lunchRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public LunchServiceImpl(LunchRepository lunchRepository, RestaurantRepository restaurantRepository) {
        this.lunchRepository = lunchRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Lunch create(@NotNull Lunch lunch, int restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found by Id"));
        lunch.setRestaurant(restaurant);
        if(lunch.getDate() == null) {
            lunch.setDate(LocalDate.now());
        }
        return lunchRepository.save(lunch);
    }
    @Override
    public Lunch update(@NotNull Lunch lunch, int restaurantId) {
        lunch.setRestaurant(restaurantRepository.getOne(restaurantId));
        checkNotFoundWithId(lunchRepository.save(lunch),lunch.getId());
        return lunch;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Lunch get(int id, int restId) {
        return lunchRepository.findByIdAndRestaurantId(id, restId)
                .orElseThrow(() -> new IllegalArgumentException("Lunch not found by id " + id));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<LunchTo> getAll(int restaurantId) {
        return LunchUtil.asTo(lunchRepository.findAllByRestaurantId(restaurantId));
    }

    @Override
    public void delete(int lunchId, int restId) {
        lunchRepository.delete(lunchId,restId);
    }

}
