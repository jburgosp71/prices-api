package com.bcnc.pricesapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Prices API",
                version = "v1",
                description = "API to check product prices by brand and date"
        )
)
public class PricesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricesApiApplication.class, args);
    }

}
