package com.example.quiz_app.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokensData {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private long expiresIn;

}
