package com.example.grad1.repository;

import com.example.grad1.domain.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=?1")
    int delete(int id);

    @EntityGraph(attributePaths = {"lunches"},type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r JOIN FETCH r.lunches d WHERE d.date =?1 ORDER BY r.name ASC ")
    List<Restaurant> findAllWithLunches(LocalDate date);

    @EntityGraph(attributePaths = {"lunches"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r JOIN FETCH r.lunches d WHERE d.date = ?1 AND r.id =?2 ORDER BY r.name ASC ")
    List<Restaurant> findAllWithLunchById(LocalDate date, int id);

    @Override
    Optional<Restaurant> findById(Integer id);
}