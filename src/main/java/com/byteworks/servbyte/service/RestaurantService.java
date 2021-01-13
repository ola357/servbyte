package com.byteworks.servbyte.service;

import com.byteworks.servbyte.model.Meal;
import com.byteworks.servbyte.repository.MealRepository;
import com.byteworks.servbyte.repository.RestaurantRepository;
import com.byteworks.servbyte.request.MealRequest;
import com.byteworks.servbyte.security.AppUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalTime;


@Service
@AllArgsConstructor
public class RestaurantService {

    private final MealRepository mealRepository;

    private final FileStorageService fileStorageService;

    private final RestaurantRepository restaurantRepository;


    @Transactional
    public String addMealToRestaurant(MealRequest request, AppUserDetails appUser) throws IOException {
        request.setOwnerPath(String.format("%s/%s", appUser.getId().toString(), "meals"));
        Meal meal = Meal.builder()
                .name(request.getName())
                .cookTime(LocalTime.of(request.getHour(), request.getMinutes(), request.getSeconds()))
                .mealPicPath(StringUtils.cleanPath(fileStorageService.storeFileToOwnerPath(request)))
                .restaurant(restaurantRepository.getOne(appUser.getId()))
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        mealRepository.save(meal);
        return "meal saved";
    }

}
