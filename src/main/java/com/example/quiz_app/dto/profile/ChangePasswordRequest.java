package com.example.quiz_app.dto.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordRequest {

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String currentPassword;

    @NotBlank(message = "New password cannot be empty")
    @Size(min = 6, max = 20, message = "New password must be between 6 and 20 characters")
    private String newPassword;

    @NotBlank(message = "Confirm password cannot be empty")
    @Size(min = 6, max = 20, message = "Confirm password must be between 6 and 20 characters")
    private String confirmPassword;

}
