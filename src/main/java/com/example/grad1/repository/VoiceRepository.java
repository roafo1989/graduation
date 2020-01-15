package com.example.grad1.repository;

import com.example.grad1.domain.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VoiceRepository extends JpaRepository<Voice, Integer> {
    @Override
    @Transactional
    Voice save(Voice voice);

    List<Voice> findByUserIdOrderByDateTimeDesc(int userId);

    @Query("SELECT v FROM Voice v WHERE v.dateTime <=:endDate AND v.dateTime >=:startDate AND v.user.id=:userId")
    Voice getMyVoice(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

    @Query("SELECT v FROM Voice v WHERE v.restaurant.id=:restaurantId AND v.dateTime <=:endDate AND v.dateTime >=:startDate")
    List<Voice> getAllByRestaurantIdAndDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Voice v WHERE v.dateTime <=:endDate AND v.dateTime >=:startDate")
    List<Voice> findAllByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT v FROM Voice v WHERE v.restaurant.id=:restaurantId")
    List<Voice> getAllByRestaurantId(@Param("restaurantId") int restaurantId);

}