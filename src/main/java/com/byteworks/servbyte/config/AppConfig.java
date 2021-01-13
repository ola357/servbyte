package com.byteworks.servbyte.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(value = "app")
public class AppConfig {

    private JwtConfig jwtConfig;

    private UploadConfig uploadConfig;

    @Data
    public static class JwtConfig {

        private String secret;

        private long expirationTime;
    }

    @Data
    public static class UploadConfig {

        private String base;
    }


}
