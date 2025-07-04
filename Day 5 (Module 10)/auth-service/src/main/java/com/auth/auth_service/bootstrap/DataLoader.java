//package com.auth.auth_service.bootstrap;
//
//import com.auth.auth_service.entity.User;
//import com.auth.auth_service.repository.UserRepository;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component // Make it a Spring component so it gets instantiated and @PostConstruct runs
//@RequiredArgsConstructor
//public class DataLoader {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @PostConstruct
//    public void init() {
//        if (userRepository.findByUsername("user").isEmpty()) {
//            userRepository.save(User.builder()
//                    .username("user")
//                    .password(passwordEncoder.encode("user123"))
//                    .roles("ROLE_USER")
//                    .build());
//        }
//        if (userRepository.findByUsername("manager").isEmpty()) {
//            userRepository.save(User.builder()
//                    .username("manager")
//                    .password(passwordEncoder.encode("manager123"))
//                    .roles("ROLE_MANAGER,ROLE_USER")
//                    .build());
//        }
//        if (userRepository.findByUsername("admin").isEmpty()) {
//            userRepository.save(User.builder()
//                    .username("admin")
//                    .password(passwordEncoder.encode("admin123"))
//                    .roles("ROLE_ADMIN,ROLE_MANAGER,ROLE_USER")
//                    .build());
//        }
//        System.out.println("Auth Service: Initial users checked/added by DataLoader.");
//    }
//}