package com.byteworks.servbyte.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class HomeController extends BaseController {


    @GetMapping
    public ResponseEntity<?> home() {
        return ResponseEntity.ok().body("https://www.baeldung.com/rest-template");
        // AppResponse<String>
        //return getResponse(OK, "welcome");
    }

}
