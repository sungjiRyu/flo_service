// CORS
package com.doyouee.flo_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry){
        // 모든 매핑 경로에 대해(addMapping("/**"))
        // 모든 사용자에 대해(allowedOrigins("*"))
        // 모든 메서드(GET, POST 등등) 허용 (allowedMethods("*"))
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
    }
    
}
