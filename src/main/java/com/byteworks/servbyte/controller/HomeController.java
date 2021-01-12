package com.byteworks.servbyte.controller;

import com.byteworks.servbyte.response.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;


@RestController
public class HomeController extends BaseController{

    @GetMapping
    public AppResponse<String> home() {
        return getResponse(OK, "welcome");
    }

}
