package com.example.quiz_app.mapper;

import com.example.quiz_app.dto.response.AvatarResponse;
import com.example.quiz_app.model.ProfileAvatar;
import org.springframework.stereotype.Component;

@Component
public class AvatarMapper {

    public AvatarResponse toResponse(ProfileAvatar profileAvatar) {
        return AvatarResponse.builder()
                .imageData(profileAvatar.getImageData())
                .contentType(profileAvatar.getContentType())
                .build();
    }

}
