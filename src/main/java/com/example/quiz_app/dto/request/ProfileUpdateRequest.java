package com.example.quiz_app.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ProfileUpdateRequest {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

}
