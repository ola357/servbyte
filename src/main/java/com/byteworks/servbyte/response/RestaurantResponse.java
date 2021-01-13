package com.byteworks.servbyte.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponse {

    private Long restaurantId;

    private String phoneNumber;

    private String name;
}
