package com.example.quiz_app.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class ProfileResponse {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;

}
