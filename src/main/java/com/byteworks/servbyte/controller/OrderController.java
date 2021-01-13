package com.byteworks.servbyte.controller;

import com.byteworks.servbyte.model.Meal;
import com.byteworks.servbyte.response.AppResponse;
import com.byteworks.servbyte.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController extends BaseController {

    private final OrderService orderService;

    @GetMapping("{restaurantId}")
    public AppResponse<List<Meal>> getRestaurantMeals(@PathVariable Long restaurantId) {
        return getResponse(HttpStatus.OK, orderService.getRestaurantMeals(restaurantId));
    }
}
