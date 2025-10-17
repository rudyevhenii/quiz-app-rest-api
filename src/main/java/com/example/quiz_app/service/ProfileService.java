package com.example.quiz_app.service;

import com.example.quiz_app.dto.profile.AvatarResponse;
import com.example.quiz_app.dto.profile.ChangePasswordRequest;
import com.example.quiz_app.dto.profile.ProfileResponse;
import com.example.quiz_app.dto.profile.ProfileUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {

    ProfileResponse getProfileByUserEmail(String email);

    ProfileResponse updateProfile(String email, ProfileUpdateRequest request);

    ProfileResponse uploadProfileImage(String email, MultipartFile imageFile);

    AvatarResponse getProfileAvatar(String email);

    void deleteProfileImage(String email);

    void changePassword(String email, ChangePasswordRequest request);

}
