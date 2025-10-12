package com.example.quiz_app.service.impl;

import com.example.quiz_app.dto.request.ChangePasswordRequest;
import com.example.quiz_app.dto.request.ProfileUpdateRequest;
import com.example.quiz_app.dto.response.AvatarResponse;
import com.example.quiz_app.dto.response.ProfileResponse;
import com.example.quiz_app.mapper.AvatarMapper;
import com.example.quiz_app.mapper.ProfileMapper;
import com.example.quiz_app.model.Profile;
import com.example.quiz_app.model.ProfileAvatar;
import com.example.quiz_app.model.User;
import com.example.quiz_app.repository.ProfileRepository;
import com.example.quiz_app.repository.UserRepository;
import com.example.quiz_app.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;
    private final AvatarMapper avatarMapper;

    @Override
    public ProfileResponse getProfileByUserEmail(String email) {
        Profile profile = getProfileByEmail(email);

        return profileMapper.toResponse(profile);
    }

    @Override
    @Transactional
    public ProfileResponse updateProfile(String email, ProfileUpdateRequest request) {
        Profile profile = getProfileByEmail(email);

        if (request.getFirstName() != null) {
            profile.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            profile.setLastName(request.getLastName());
        }
        if (request.getDateOfBirth() != null) {
            profile.setDateOfBirth(request.getDateOfBirth());
        }

        profileRepository.save(profile);

        return profileMapper.toResponse(profile);
    }

    @Override
    @Transactional
    public ProfileResponse uploadProfileImage(String email, MultipartFile imageFile) {
        Profile profile = getProfileByEmail(email);

        try {
            byte[] bytes = imageFile.getBytes();
            String contentType = imageFile.getContentType();

            ProfileAvatar profileAvatar = ProfileAvatar.builder()
                    .imageData(bytes)
                    .contentType(contentType)
                    .build();

            profile.setProfileAvatar(profileAvatar);
            profileAvatar.setProfile(profile);

            profileRepository.save(profile);
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + imageFile.getOriginalFilename());
        }

        return profileMapper.toResponse(profile);
    }

    @Override
    public AvatarResponse getProfileAvatar(String email) {
        Profile profile = getProfileByEmail(email);
        ProfileAvatar profileAvatar = profile.getProfileAvatar();

        if (profileAvatar == null) {
            throw new RuntimeException("Avatar not found for user: " + email);
        }
        return avatarMapper.toResponse(profileAvatar);
    }

    @Override
    @Transactional
    public void deleteProfileImage(String email) {
        Profile profile = getProfileByEmail(email);

        if (profile.getProfileAvatar() != null) {
            profile.setProfileAvatar(null);
        }
    }

    @Override
    @Transactional
    public void changePassword(String email, ChangePasswordRequest request) {
        Profile profile = getProfileByEmail(email);
        User user = profile.getUser();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Current password does not match.");
        }
        String newPassword = request.getNewPassword();
        String confirmPassword = request.getConfirmPassword();

        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("New password and confirmation password do not match.");
        }
        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }

    private Profile getProfileByEmail(String email) {
        return profileRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("User profile is not found with email: " + email));
    }

}
