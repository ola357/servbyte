package com.byteworks.servbyte.controller;

import com.byteworks.servbyte.request.LoginRequest;
import com.byteworks.servbyte.request.SignUpRequest;
import com.byteworks.servbyte.response.AppResponse;
import com.byteworks.servbyte.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController extends BaseController {

    private final AuthService authService;

    @PostMapping(path = "/signup", consumes = {"multipart/form-data"})
    public AppResponse<Map<String, String>> signUp(@Valid SignUpRequest request) throws IOException {
        return getResponse(HttpStatus.CREATED, authService.signUp(request));
    }

    @PostMapping("/login")
    public AppResponse<Map<String, String>> login(@Valid @RequestBody LoginRequest request) {
        return getResponse(HttpStatus.OK, authService.login(request));
    }

}
