package com.byteworks.servbyte.controller;

import com.byteworks.servbyte.response.AppResponse;
import com.byteworks.servbyte.service.DeliveryCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryCompanyController extends BaseController {

    private final DeliveryCompanyService deliveryCompanyService;


    @GetMapping("channels")
    public AppResponse<?> getAllDeliveryChannels() {
        return getResponse(OK, deliveryCompanyService.getAllDeliveryChannels());
    }

}
