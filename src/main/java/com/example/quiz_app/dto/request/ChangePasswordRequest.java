package com.example.quiz_app.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordRequest {

    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

}
