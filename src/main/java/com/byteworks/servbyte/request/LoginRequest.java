package com.byteworks.servbyte.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
