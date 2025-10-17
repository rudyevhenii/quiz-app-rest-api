package com.example.quiz_app.service;

import com.example.quiz_app.dto.TokensData;
import com.example.quiz_app.dto.auth.LoginRequest;
import com.example.quiz_app.dto.auth.RegisterRequest;
import jakarta.servlet.http.Cookie;

public interface AuthenticationService {

    TokensData register(RegisterRequest request);

    TokensData login(LoginRequest request);

    TokensData refreshToken(Cookie[] cookies);

}
