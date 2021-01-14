package com.byteworks.servbyte;

import com.byteworks.servbyte.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
@EnableConfigurationProperties(value = {AppConfig.class})
public class ServbyteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServbyteApplication.class, args);
    }

    @Bean
    public Docket swaggerEmployeeApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.byteworks.servbyte.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0")
                        .title("servbyte")
                        .description("servbyte application")
                        .build());
    }


    @Bean("restTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
