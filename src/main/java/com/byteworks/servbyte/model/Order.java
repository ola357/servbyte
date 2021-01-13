package com.byteworks.servbyte.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private Long restaurantId;

    private String restaurantName;

    @OneToOne
    private Meal meal;

    @Transient
    public PaymentRequest buildPaymentRequest(){
        return PaymentReq
    }

}
