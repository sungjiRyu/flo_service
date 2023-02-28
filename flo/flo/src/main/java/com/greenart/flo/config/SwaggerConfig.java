package com.greenart.flo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI floOpenAPI() {
        Info info = new Info().version("0.0.1").title("FLO서비스 API").description("FLO서비스 Restful API 명세서");
        return new OpenAPI().info(info);
    }
}
