package com.example.quiz_app.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;

}
