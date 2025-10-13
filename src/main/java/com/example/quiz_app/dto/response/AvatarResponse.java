package com.example.quiz_app.dto.response;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class AvatarResponse {

    private byte[] imageData;
    private String contentType;

}
