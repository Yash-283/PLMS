package com.auth.auth_service.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;

    // ✅ Make sure these methods are present:
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
