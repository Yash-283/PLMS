// src/main/java/com/auth/auth_service/entity/User.java
package com.auth.auth_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user") // Avoid 'user' as it can be a reserved keyword in some DBs
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // Change from String roles; to Role role;
    @Enumerated(EnumType.STRING) // Store the enum as a String in the database (e.g., "USER", "ADMIN")
    @Column(nullable = false) // Role should generally not be null
    private Role role; // Now a single Role enum

    // --- UserDetails interface methods ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the single Role enum to a collection of GrantedAuthority
        // The authority string (e.g., "ROLE_USER") is retrieved from the enum itself.
        return List.of(new SimpleGrantedAuthority(role.getAuthority()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}