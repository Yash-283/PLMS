//package com.auth.auth_service.security;
//
//import com.auth.auth_service.entity.Role;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Date;
//
//@Service
//public class JwtService {
//
//    private static final long EXPIRATION_TIME = 86400000; // 1 day
//    private static final String SECRET = "my-secret-key-which-should-be-long-and-secure";
//
//    private Key getSignInKey() {
//        return Keys.hmacShaKeyFor(SECRET.getBytes());
//    }
//
//    public String generateToken(String username, Role role) {
//        return Jwts.builder()
//                .setSubject(username)
//                .claim("role", role.name())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String extractUsername(String token) {
//        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    public String extractRole(String token) {
//        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build()
//                .parseClaimsJws(token)
//                .getBody()
//                .get("role", String.class);
//    }
//
//    public boolean isTokenValid(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
//            return true;
//        } catch (JwtException e) {
//            return false;
//        }
//    }
//}
