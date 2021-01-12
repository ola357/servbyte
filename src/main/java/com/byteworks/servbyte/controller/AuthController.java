package com.byteworks.servbyte.controller;

import com.byteworks.servbyte.request.LoginRequest;
import com.byteworks.servbyte.request.SignUpRequest;
import com.byteworks.servbyte.response.AppResponse;
import com.byteworks.servbyte.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController extends BaseController{

    private final AuthService authService;

    @PostMapping("/signup")
    public AppResponse<Map<String, String>> signUp(@Valid @RequestBody SignUpRequest request){
        return getResponse(HttpStatus.CREATED, authService.signUp(request));
    }

    @PostMapping("/login")
    public AppResponse<Map<String, String>> login(@Valid @RequestBody LoginRequest request){
        return getResponse(HttpStatus.CREATED, authService.login(request));
    }

}
