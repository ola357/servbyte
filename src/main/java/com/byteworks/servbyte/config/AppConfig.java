package com.byteworks.servbyte.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(value = "app")
public class AppConfig {

    private JwtConfig jwtConfig;

    @Data
    public static class JwtConfig {

        private String secret;

        private long expirationTime;
    }

}
