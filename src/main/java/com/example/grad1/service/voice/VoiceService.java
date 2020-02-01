package com.example.grad1.service.voice;

import com.example.grad1.domain.Restaurant;
import com.example.grad1.domain.User;
import com.example.grad1.domain.Voice;
import com.example.grad1.to.voiceTo.VoiceTo;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface VoiceService {
    @Transactional
    Voice create(int userId, int restaurantId);

    List<VoiceTo> getByUserIdAndDate(int userId, String dateStr);
    List<VoiceTo> getAllByUserId(int userId);

    List<VoiceTo> getByRestaurantBetweenDates(String startDate, String endDate, int restaurantId);
    List<VoiceTo> getByRestaurantIdAndDate(String date, int restaurantId);
    List<VoiceTo> getByRestaurantId(int restaurantId);

    Map<String, Integer> getRatingByDate(String dateTime);
    Map<String, Integer> getRating();

    List<VoiceTo> getAllByDate(String dateTime);
    List<VoiceTo> getAll();

    Map<Restaurant, List<VoiceTo>> getAllByRestaurantIdAndDate(String dateTime);
    Map<Restaurant, List<VoiceTo>> getAllByRestaurantId();

}
