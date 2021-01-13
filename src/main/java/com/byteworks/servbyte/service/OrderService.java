package com.byteworks.servbyte.service;

import com.byteworks.servbyte.model.Meal;
import com.byteworks.servbyte.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final MealRepository mealRepository;

    public List<Meal> getRestaurantMeals(Long restaurantId){
        return mealRepository.findMealsByRestaurantId(restaurantId);
    }

}
