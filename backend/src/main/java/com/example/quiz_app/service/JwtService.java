package com.example.quiz_app.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String TOKEN_TYPE = "token_type";

    @Value("${app.security.jwt.secret-key}")
    private String secretKey;

    @Value("${app.security.jwt.access-token.expiration}")
    private long accessTokenExpiration;

    @Value("${app.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> claims = Map.of(TOKEN_TYPE, "ACCESS_TOKEN");
        return generateToken(claims, userDetails, accessTokenExpiration);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = Map.of(TOKEN_TYPE, "REFRESH_TOKEN");
        return generateToken(claims, userDetails, refreshTokenExpiration);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails,
                                long expiration) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(now))
                .expiration(new Date(now + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new RuntimeException("Invalid JWT token, " + e);
        }
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String email = extractEmail(token);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    public String refreshAccessToken(UserDetails userDetails, String refreshToken) {
        Claims claims = extractAllClaims(refreshToken);
        if (!"REFRESH_TOKEN".equals(claims.get(TOKEN_TYPE))) {
            throw new RuntimeException("Invalid token type");
        }
        if (isTokenExpired(refreshToken)) {
            throw new RuntimeException("Refresh Token expired");
        }

        return generateAccessToken(userDetails);
    }

}
