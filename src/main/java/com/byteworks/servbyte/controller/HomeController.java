package com.byteworks.servbyte.controller;

import com.byteworks.servbyte.response.AppResponse;
import com.byteworks.servbyte.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequiredArgsConstructor
public class HomeController extends BaseController{



    @GetMapping
    public ResponseEntity<?> home() {
        return ResponseEntity.ok().body("https://www.baeldung.com/rest-template");
        // AppResponse<String>
        //return getResponse(OK, "welcome");
    }

}
