package com.example.quiz_app.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AvatarResponse {

    private byte[] imageData;
    private String contentType;

}
