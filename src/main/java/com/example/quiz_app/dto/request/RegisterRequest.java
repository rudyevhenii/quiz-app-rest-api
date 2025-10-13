package com.example.quiz_app.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
