package com.example.quiz_app.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

    private String email;
    private String password;

}
