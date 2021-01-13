package com.byteworks.servbyte.repository;

import com.byteworks.servbyte.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MealRepository extends JpaRepository<Meal, Long> {


    List<Meal> findMealsByRestaurantId(Long restaurantId);
}
