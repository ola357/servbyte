package com.byteworks.servbyte.controller;

import com.byteworks.servbyte.model.Meal;
import com.byteworks.servbyte.request.OrderRequest;
import com.byteworks.servbyte.response.AppResponse;
import com.byteworks.servbyte.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController extends BaseController {

    private final OrderService orderService;

    @GetMapping("{restaurantId}")
    public AppResponse<List<Meal>> getRestaurantMeals(@PathVariable Long restaurantId) {
        return getResponse(HttpStatus.OK, orderService.getRestaurantMeals(restaurantId));
    }

    @PostMapping
    public Map<String, String> orderAMeal(@Valid @RequestBody OrderRequest orderRequest){
        return orderService.orderAMeal(orderRequest);
    }
}
