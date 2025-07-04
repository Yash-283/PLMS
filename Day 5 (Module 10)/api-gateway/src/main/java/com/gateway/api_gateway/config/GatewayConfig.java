package com.gateway.api_gateway.config;



import com.gateway.api_gateway.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.web.server.WebFilter;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final JwtAuthFilter jwtFilter;

    @Bean
    public WebFilter securityWebFilter() {
        return jwtFilter; // ✅ global filter
    }
}
