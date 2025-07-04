package com.config.config_server.security; // adjust to your package

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/encrypt", "/decrypt").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults()) // Enables basic auth
                .csrf(csrf -> csrf.disable()); // Disable CSRF for POST requests via Postman

        return http.build();
    }
}
