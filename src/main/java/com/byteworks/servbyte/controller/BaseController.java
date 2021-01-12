package com.byteworks.servbyte.controller;

import com.byteworks.servbyte.response.AppResponse;
import org.springframework.http.HttpStatus;


public class BaseController {
    public BaseController() {
    }

    public <T> AppResponse<T> getResponse(HttpStatus status, T data) {
        return data == null ? this.getFailResponse(status, new NullPointerException()) : this.getSuccessResponse(status, data);
    }

    public <T> AppResponse<T> getSuccessResponse(HttpStatus status, T data) {
        return new AppResponse<>(status, "success", data);
    }

    public <T> AppResponse<T> getFailResponse(HttpStatus status, Throwable ex) {
        return new AppResponse<>(status, ex);
    }


}

