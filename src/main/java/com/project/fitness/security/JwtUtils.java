package com.project.fitness.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    private static final String JWT_SECRET =
            "cmlzaGFiaHBhdGhha2lzdmVyeWdvb2RhbmRpc2dyZWF0cGVyc29u";

    private static final long JWT_EXPIRATION_MS = 172800000;

    public String generateToken(String userId , String role) {
        return Jwts.builder()
                .subject(userId)
                .claim("roles", List.of(role))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(key())
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((javax.crypto.SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
    }
}
