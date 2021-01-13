package com.byteworks.servbyte.controller;

import com.byteworks.servbyte.model.Meal;
import com.byteworks.servbyte.request.MealRequest;
import com.byteworks.servbyte.response.AppResponse;
import com.byteworks.servbyte.security.AppUser;
import com.byteworks.servbyte.security.AppUserDetails;
import com.byteworks.servbyte.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;


@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController extends BaseController {

    private final RestaurantService restaurantService;

    @PostMapping("meal")
    public AppResponse<?> addMealToRestaurant(@Valid MealRequest request, @AppUser AppUserDetails appUserDetails)
            throws IOException {
        return getResponse(HttpStatus.CREATED, restaurantService.addMealToRestaurant(request, appUserDetails));
    }



}
