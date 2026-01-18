package com.example.foodordering.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    // 1. The key must be long enough for HS256 (at least 32 characters/256 bits)
    private final String SECRET_STRING = "mysecretkey_must_be_at_least_32_characters_long!!";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


//import java.util.Date;
//
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class JwtUtil {
//	 private final String SECRET = "mysecretkeymysecretkeymysecretkey";
//	 
//	 public String generateToken(String email, String role) {
//		 return Jwts.builder()
//				 .setSubject(email)
//				 .claim("role", role)
//				 .setIssuedAt(new Date())
//				 .setExpiration(new Date(System.currentTimeMillis() + 3600000))
//				 .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
//	             .compact();
//	 }
//	 
//	 public String extractEmail(String token) {
//		 return getClaims(token).getSubject();
//	 }
//	 
//	 private Claims getClaims(String token) {
//		 return Jwts.parserBuilder()
//				 .setSigningKey(SECRET.getBytes())
//				 .build()
//				 .parseClaimsJws(token)
//				 .getBody();
//	 }
//}
