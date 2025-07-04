// src/main/java/com/gateway/api_gateway/util/JwtUtil.java
package com.gateway.api_gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component; // Or @Service

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component // Or @Service
public class JwtUtil { // Renamed from JwtService in earlier discussions, but functionality is similar

    // IMPORTANT: This secret key MUST be the same as in auth-service's JwtService
    @Value("${jwt.secret}")
    private String secretKey;

    // Extracts the username (subject) from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extracts roles from the token (assuming "roles" claim is a comma-separated string)
    public String extractRoles(String token) {
        return extractClaim(token, claims -> claims.get("roles", String.class));
    }

    // Extracts a specific claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extracts all claims from the token, also implicitly validates signature and expiration
    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Validates if the token is valid (signature, expiration)
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token); // This will throw if invalid or expired
            return true;
        } catch (Exception e) {
            System.err.println("JWT Validation Error in API Gateway: " + e.getMessage());
            return false;
        }
    }

    // Decodes the secret key to create a signing key
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}