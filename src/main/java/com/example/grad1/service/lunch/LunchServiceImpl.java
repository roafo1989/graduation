package com.example.grad1.service.lunch;

import com.example.grad1.domain.Lunch;
import com.example.grad1.repository.LunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.example.grad1.util.ValidationUtil.checkNotFoundWithId;


@Service
@Transactional
public class LunchServiceImpl implements LunchService {
    private final LunchRepository lunchRepository;
    @Autowired
    public LunchServiceImpl(LunchRepository lunchRepository) {
        this.lunchRepository = lunchRepository;
    }

/*    private final RestaurantRepository restaurantRepository;

    @Autowired
    public LunchServiceImpl(LunchRepository lunchRepository, RestaurantRepository restaurantRepository) {
        this.lunchRepository = lunchRepository;
        this.restaurantRepository = restaurantRepository;
    }*/

    @Override
    public Lunch create(@NotNull Lunch lunch) {
        return lunchRepository.save(lunch);
    }
    @Override
    public void update(@NotNull Lunch lunch) {
        checkNotFoundWithId(lunchRepository.save(lunch),lunch.getId());
    }

    @Override
    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(lunchRepository.delete(id, restaurantId) != 0, id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Lunch get(int id, int restaurantId) {
        return checkNotFoundWithId(lunchRepository.findByIdAndRestaurantId(id, restaurantId), id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Lunch> getAll(int restaurantId) {
        return lunchRepository.findAllByRestaurantId(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("not found dishes with restaurant id " + restaurantId));
    }


}
