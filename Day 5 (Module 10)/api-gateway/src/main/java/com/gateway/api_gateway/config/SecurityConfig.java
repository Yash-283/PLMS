//// src/main/java/com/gateway/api_gateway/config/SecurityConfig.java
//package com.gateway.api_gateway.config;
//
//import com.gateway.api_gateway.filter.JwtAuthFilter; // Use JwtAuthFilter
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.SecurityWebFilterChain;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
//import org.springframework.security.web.server.SecurityWebFiltersOrder; // Make sure this is imported
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private JwtAuthFilter jwtAuthFilter; // Autowire your JwtAuthFilter
//
//    private static final String[] PUBLIC_MATCHERS = {
//            "/auth/**",     // Covers /auth/login, /auth/register, etc.
//            "/eureka/**"
//    };
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers(PUBLIC_MATCHERS).permitAll() // Permit public paths
//                        .anyExchange().authenticated() // All others require authentication
//                )
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                        .authenticationEntryPoint((exchange, e) -> {
//                            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
//                            return exchange.getResponse().setComplete();
//                        })
//                        .accessDeniedHandler((exchange, e) -> {
//                            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.FORBIDDEN);
//                            return exchange.getResponse().setComplete();
//                        })
//                )
//                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
//                // CRITICAL: Add your JwtAuthFilter here.
//                // We place it at AUTHENTICATION to ensure it runs before Spring Security's
//                // default authentication mechanisms.
//                .addFilterAt(jwtAuthFilter, SecurityWebFiltersOrder.AUTHENTICATION)
//                .build();
//    }
//}