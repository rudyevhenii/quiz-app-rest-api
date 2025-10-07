package com.example.quiz_app.service.impl;

import com.example.quiz_app.dto.TokensData;
import com.example.quiz_app.dto.request.LoginRequest;
import com.example.quiz_app.dto.request.RegisterRequest;
import com.example.quiz_app.enums.UserRole;
import com.example.quiz_app.model.Profile;
import com.example.quiz_app.model.Role;
import com.example.quiz_app.model.User;
import com.example.quiz_app.repository.UserRepository;
import com.example.quiz_app.service.AuthenticationService;
import com.example.quiz_app.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    public static final String BEARER = "Bearer";

    @Value("${app.security.jwt.access-token.expiration}")
    public long accessTokenExpiration;

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public TokensData register(RegisterRequest request) {
        Profile profile = Profile.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        Role role = Role.builder()
                .name(UserRole.USER)
                .build();

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .profile(profile)
                .role(role)
                .build();

        userRepository.save(user);

        return generateTokens(user);
    }

    @Override
    public TokensData login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = (User) auth.getPrincipal();

        return generateTokens(user);
    }

    @Override
    public TokensData refreshToken(Cookie[] cookies) {
        String refreshToken = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("refresh-token"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new RuntimeException("Refresh token missing"));

        String email = jwtService.extractClaim(refreshToken, Claims::getSubject);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist with email: " + email));

        String accessToken = jwtService.refreshAccessToken(user, refreshToken);
        String newRefreshToken = jwtService.generateRefreshToken(user);

        return TokensData.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .tokenType(BEARER)
                .expiresIn(Duration.ofMillis(accessTokenExpiration).toSeconds())
                .build();
    }

    private TokensData generateTokens(User user) {
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return TokensData.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(BEARER)
                .expiresIn(Duration.ofMillis(accessTokenExpiration).toSeconds())
                .build();
    }

}
