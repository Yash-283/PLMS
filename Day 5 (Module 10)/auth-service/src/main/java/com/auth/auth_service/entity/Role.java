// src/main/java/com/auth/auth_service/entity/Role.java
package com.auth.auth_service.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum Role {
    // Define your roles here. Spring Security typically expects roles to start with "ROLE_"
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    @Getter
    private final String authority; // Stores the Spring Security compatible role string


}