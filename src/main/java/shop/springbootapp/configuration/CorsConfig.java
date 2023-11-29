package shop.springbootapp.configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/api/products/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET");
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
