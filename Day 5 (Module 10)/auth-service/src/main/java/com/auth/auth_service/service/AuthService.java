package com.auth.auth_service.service;

import com.auth.auth_service.dto.AuthResponse;
import com.auth.auth_service.dto.LoginRequest;
import com.auth.auth_service.dto.RegisterRequest;
import com.auth.auth_service.entity.Role;
import com.auth.auth_service.entity.User;
import com.auth.auth_service.repository.UserRepository;
import com.auth.auth_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.valueOf(request.role()));
        userRepository.save(user);

        // ✅ Generate token using username and role
        String token = jwtUtil.generateToken(user.getUsername(), String.valueOf(user.getRole()));
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())  // ✅
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername(), String.valueOf(user.getRole()));
        return new AuthResponse(token);
    }


}
