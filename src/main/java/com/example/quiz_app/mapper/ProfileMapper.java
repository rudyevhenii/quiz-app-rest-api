package com.example.quiz_app.mapper;

import com.example.quiz_app.dto.response.ProfileResponse;
import com.example.quiz_app.model.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public ProfileResponse toResponse(Profile profile) {
        return ProfileResponse.builder()
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .dateOfBirth(profile.getDateOfBirth())
                .email(profile.getUser().getEmail())
                .build();
    }

}
