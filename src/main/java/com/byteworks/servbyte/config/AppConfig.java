package com.byteworks.servbyte.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(value = "app")
public class AppConfig {

    private JwtConfig jwtConfig;

    private UploadConfig uploadConfig;

    private PaymentConfig paymentConfig;


    @Data
    public static class JwtConfig {

        private String secret;

        private long expirationTime;
    }


    @Data
    public static class UploadConfig {

        private String base;
    }


    @Data
    public static class PaymentConfig {

        private String url;

        private String authorization;
    }

}
