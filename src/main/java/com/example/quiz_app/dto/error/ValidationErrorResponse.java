package com.example.quiz_app.dto.error;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorResponse(
        int status,
        Map<String, String> errors,
        LocalDateTime timestamp
) {
}
