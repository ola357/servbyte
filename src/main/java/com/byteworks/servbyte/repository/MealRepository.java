package com.byteworks.servbyte.repository;

import com.byteworks.servbyte.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MealRepository extends JpaRepository<Meal, Long> {

}
