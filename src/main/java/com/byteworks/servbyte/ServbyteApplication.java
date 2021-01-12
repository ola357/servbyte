package com.byteworks.servbyte;

import com.byteworks.servbyte.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(value = { AppConfig.class })
public class ServbyteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServbyteApplication.class, args);
    }

}
