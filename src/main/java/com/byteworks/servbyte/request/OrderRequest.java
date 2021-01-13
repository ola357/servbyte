package com.byteworks.servbyte.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

    private String city;

    private Long restaurantId;

    private Long mealId;

    @Positive
    private Double payment;

    private String requesterPhoneNumber;

    private String requesterEmail;

}
