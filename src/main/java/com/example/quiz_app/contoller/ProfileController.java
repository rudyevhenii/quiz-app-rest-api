package com.example.quiz_app.contoller;

import com.example.quiz_app.dto.request.ChangePasswordRequest;
import com.example.quiz_app.dto.request.ProfileUpdateRequest;
import com.example.quiz_app.dto.response.AvatarResponse;
import com.example.quiz_app.dto.response.ProfileResponse;
import com.example.quiz_app.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ProfileResponse> getProfile(Authentication authentication) {
        String email = authentication.getName();
        ProfileResponse response = profileService.getProfileByUserEmail(email);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update-profile")
    public ResponseEntity<ProfileResponse> updateProfile(Authentication authentication,
                                                         @Valid @RequestBody ProfileUpdateRequest request) {
        String email = authentication.getName();
        ProfileResponse response = profileService.updateProfile(email, request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload-image")
    public ResponseEntity<ProfileResponse> uploadProfileImage(Authentication authentication,
                                                              @RequestParam("image") MultipartFile multipartFile) {
        String email = authentication.getName();
        ProfileResponse response = profileService.uploadProfileImage(email, multipartFile);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getProfileImage(Authentication authentication) {
        String email = authentication.getName();
        AvatarResponse response = profileService.getProfileAvatar(email);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(response.getContentType()))
                .contentLength(response.getImageData().length)
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(response.getImageData());
    }

    @DeleteMapping("/delete-image")
    public ResponseEntity<Void> deleteProfileImage(Authentication authentication) {
        String email = authentication.getName();
        profileService.deleteProfileImage(email);

        return ResponseEntity.noContent()
                .build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changeUserPassword(Authentication authentication,
                                                   @Valid @RequestBody ChangePasswordRequest request) {
        String email = authentication.getName();
        profileService.changePassword(email, request);

        return ResponseEntity.ok()
                .build();
    }

}
