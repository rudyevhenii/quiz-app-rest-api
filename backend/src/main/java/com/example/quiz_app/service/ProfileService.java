package com.example.quiz_app.service;

import com.example.quiz_app.dto.request.ChangePasswordRequest;
import com.example.quiz_app.dto.request.ProfileUpdateRequest;
import com.example.quiz_app.dto.response.AvatarResponse;
import com.example.quiz_app.dto.response.ProfileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {

    ProfileResponse getProfileByUserEmail(String email);

    ProfileResponse updateProfile(String email, ProfileUpdateRequest request);

    ProfileResponse uploadProfileImage(String email, MultipartFile imageFile);

    AvatarResponse getProfileAvatar(String email);

    void deleteProfileImage(String email);

    void changePassword(String email, ChangePasswordRequest request);

}
