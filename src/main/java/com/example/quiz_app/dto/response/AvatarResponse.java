package com.example.quiz_app.dto.response;

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
