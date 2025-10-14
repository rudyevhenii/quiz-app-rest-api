package com.example.quiz_app.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageValidationService {

    public static final List<String> ALLOWED_MIME_TYPES = List.of(
            "image/jpeg",
            "image/png",
            "image/gif",
            "image/webp"
    );

    private final Tika tika;

    public void validateImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is empty or null");
        }

        try {
            String detectedType = tika.detect(file.getInputStream());

            if (!ALLOWED_MIME_TYPES.contains(detectedType)) {
                throw new RuntimeException("Invalid file type. " +
                        "Only JPEG, PNG, GIF, WEBP images are allowed. Detected: " + detectedType);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
