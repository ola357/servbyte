package com.byteworks.servbyte.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest implements Serializable {

    private Double amount;

    private String email;
}
