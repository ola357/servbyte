package com.byteworks.servbyte.service;

import com.byteworks.servbyte.model.Meal;
import com.byteworks.servbyte.repository.MealRepository;
import com.byteworks.servbyte.request.OrderRequest;
import com.byteworks.servbyte.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final MealRepository mealRepository;

    private final PaymentService paymentService;


    public List<Meal> getRestaurantMeals(Long restaurantId) {
        return mealRepository.findMealsByRestaurantId(restaurantId);
    }


    public Map<String, String> orderAMeal(OrderRequest order) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(order.getPayment());
        paymentRequest.setEmail(order.getRequesterEmail());
        return Map.of("message", "click on the authorization url to complete payment", "authorizationUrl",
                paymentService.pay(paymentRequest).getData().getAuthorization_url());
    }

}
