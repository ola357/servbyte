package com.byteworks.servbyte.service;

import com.byteworks.servbyte.config.AppConfig;
import com.byteworks.servbyte.request.PaymentRequest;
import com.byteworks.servbyte.response.PaymentResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final RestTemplate restTemplate;

    private final AppConfig appConfig;

    public static final ParameterizedTypeReference<String> PARAMETERIZED_RESPONSE_TYPE = new ParameterizedTypeReference<>() {

    };

    public ObjectMapper mapper = new ObjectMapper();


    public PaymentResponse pay(PaymentRequest paymentRequest) {
        String url = appConfig.getPaymentConfig().getUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", String.format("%s %s", "Bearer", appConfig.getPaymentConfig().getAuthorization()));
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        RequestEntity<?> request = new RequestEntity<>(paymentRequest, headers, HttpMethod.POST, uri);
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(request, PARAMETERIZED_RESPONSE_TYPE);
            getObjectFromJson(getDecodedResponse(Objects.requireNonNull(response.getBody())),
                    PaymentResponse.class);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }

        return getObjectFromJson(getDecodedResponse(Objects.requireNonNull(response.getBody())),
                PaymentResponse.class);
    }

    protected String getDecodedResponse(String response) {
        String decodedResponse = response;
        if (!response.startsWith("{") && !response.startsWith("[")) {
            decodedResponse = new String(Base64.getDecoder().decode(response));
        }
        return decodedResponse;
    }


    public <T> T getObjectFromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }


}
