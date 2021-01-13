package com.byteworks.servbyte.model;

import com.byteworks.servbyte.request.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;


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

    @Email
    private String requesterEmail;

    @OneToOne
    private Meal meal;



}
