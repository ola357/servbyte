package com.byteworks.servbyte.service;

import com.byteworks.servbyte.config.AppConfig;
import com.byteworks.servbyte.request.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.iyanuadelekan.paystackjava.core.ApiConnection;
import org.json.JSONObject;
import me.iyanuadelekan.paystackjava.core.ApiQuery;
import me.iyanuadelekan.paystackjava.core.Customers;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final RestTemplate restTemplate;

    private final AppConfig appConfig;

    public static final ParameterizedTypeReference<String> PARAMETERIZED_RESPONSE_TYPE = new ParameterizedTypeReference<>() {

    };


    public ResponseEntity<String> pay(PaymentRequest paymentRequest) {
        String url = appConfig.getPaymentConfig().getUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", String.format("%s %s", "Bearer", appConfig.getPaymentConfig().getAuthorization()));
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        RequestEntity<?> request = new RequestEntity<>(paymentRequest, headers, HttpMethod.POST, uri);
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(request, PARAMETERIZED_RESPONSE_TYPE);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }

        return response;
    }

}
