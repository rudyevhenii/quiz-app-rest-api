package com.example.quiz_app.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

    private String accessToken;
    private String tokenType;
    private long expiresIn;

}
