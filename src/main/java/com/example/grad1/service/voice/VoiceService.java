package com.example.grad1.service.voice;

import com.example.grad1.domain.Restaurant;
import com.example.grad1.to.voiceTo.VoiceTo;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface VoiceService {
    VoiceTo getByUserId(int userId, LocalDateTime dateTime);

    @Transactional
    VoiceTo create(int userId, int restaurantId);

    List<VoiceTo> getByRestaurantIdAndDate(@NotNull LocalDateTime date, int restaurantId);

    Map<Restaurant, List<VoiceTo>> getAllByRestaurantIdAndDate(@NotNull LocalDateTime dateTime);

    Map<String, Integer> getRatingByDate(LocalDateTime dateTime);

    List<VoiceTo> getAllByUserId(int userId);

    List<VoiceTo> getAllByDate(LocalDateTime dateTime);

    List<VoiceTo> getByRestaurantBetweenDates(String startDate, String endDate, int restaurantId);
}
