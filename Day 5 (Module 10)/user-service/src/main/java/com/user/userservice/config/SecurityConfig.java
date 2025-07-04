package com.user.userservice.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Enable Spring Security for this service
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless API
                .authorizeHttpRequests(authorize -> authorize
                        // All requests reaching this service are assumed to be authenticated by the API Gateway.
                        // You can add more granular authorization here based on roles if needed:
                        // .requestMatchers("/users/admin/**").hasRole("ADMIN")
                        // .requestMatchers("/users/profile").authenticated()
                        .anyRequest().authenticated() // Ensure all requests are authenticated
                )
                // Configure stateless session management
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    // No need for PasswordEncoder or AuthenticationManager here,
    // as authentication is handled by the API Gateway.
    // If you had a custom UserDetailsService here, you might still need it
    // if you're loading user details for internal service logic,
    // but not for authentication itself.
}
