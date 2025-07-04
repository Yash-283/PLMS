package com.auth.auth_service.controller;

import com.auth.auth_service.dto.AuthResponse;
import com.auth.auth_service.dto.LoginRequest;
import com.auth.auth_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest authRequest) {
        if ("admin".equals(authRequest.getUsername()) && "admin".equals(authRequest.getPassword())) {

            // ✅ Extract username and role
            String username = authRequest.getUsername();
            String role = "MANAGER"; // hardcoded or fetched from DB later

            // ✅ Call JwtUtil with (username, role)
            String token = jwtUtil.generateToken(username, role);

            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
