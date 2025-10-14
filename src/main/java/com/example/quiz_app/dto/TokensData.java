package com.example.quiz_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TokensData {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private long expiresIn;

}
