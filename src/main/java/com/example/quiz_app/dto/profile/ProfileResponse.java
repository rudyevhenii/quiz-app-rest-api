package com.example.quiz_app.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class ProfileResponse {

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;

}
