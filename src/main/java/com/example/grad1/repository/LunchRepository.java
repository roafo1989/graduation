package com.example.grad1.repository;

import com.example.grad1.domain.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface LunchRepository extends JpaRepository<Lunch, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Lunch c WHERE c.id=?1 AND c.restaurant.id =?2")
    int delete(int id, int restaurantId);

    Lunch findByIdAndRestaurantId(int id, int restaurantId);

    Optional<List<Lunch>> findAllByRestaurantId(@Param("restaurantId") int restaurantId);
}