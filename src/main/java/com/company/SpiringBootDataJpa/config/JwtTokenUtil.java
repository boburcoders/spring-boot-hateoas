package com.company.SpiringBootDataJpa.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil {
    // New Base64-encoded key (decodes to 32 bytes)
    private final static String SECRET_KEY = "dGhpc2lzYXNlY3VyZWtleTEyMzQ1Njc4OTAxMjM0NTY=";

    public String generateToken(@NonNull String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // 10 minutes
                .signWith(signKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean isValid(@NonNull String token) {
        try {
            Claims claims = getClaims(token);
            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUsername(@NonNull String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }


    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key signKey() {
        byte[] bytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }
}
