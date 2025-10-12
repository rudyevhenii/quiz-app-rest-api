package com.example.quiz_app.contoller;

import com.example.quiz_app.dto.TokensData;
import com.example.quiz_app.dto.request.LoginRequest;
import com.example.quiz_app.dto.request.RegisterRequest;
import com.example.quiz_app.dto.response.AuthResponse;
import com.example.quiz_app.service.AuthenticationService;
import com.example.quiz_app.service.impl.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Value("${app.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest,
                                                 HttpServletResponse response) {
        TokensData tokensData = authenticationService.register(registerRequest);
        addRefreshTokenCookie(tokensData, response);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthResponse.builder()
                        .accessToken(tokensData.getAccessToken())
                        .tokenType(tokensData.getTokenType())
                        .expiresIn(tokensData.getExpiresIn())
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest,
                                              HttpServletResponse response) {
        TokensData tokensData = authenticationService.login(loginRequest);
        addRefreshTokenCookie(tokensData, response);

        return ResponseEntity.status(HttpStatus.OK)
                .body(AuthResponse.builder()
                        .accessToken(tokensData.getAccessToken())
                        .tokenType(tokensData.getTokenType())
                        .expiresIn(tokensData.getExpiresIn())
                        .build());
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(HttpServletRequest request,
                                                     HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        TokensData tokensData = authenticationService.refreshToken(cookies);

        addRefreshTokenCookie(tokensData, response);

        return ResponseEntity.status(HttpStatus.OK)
                .body(AuthResponse.builder()
                        .accessToken(tokensData.getAccessToken())
                        .tokenType(tokensData.getTokenType())
                        .expiresIn(tokensData.getExpiresIn())
                        .build());
    }

    private void addRefreshTokenCookie(TokensData tokensData, HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("refresh-token", tokensData.getRefreshToken())
                .httpOnly(true)
                .secure(false)
                .path("/api/v1/auth")
                .maxAge(Duration.ofMillis(refreshTokenExpiration).toSeconds())
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

}
