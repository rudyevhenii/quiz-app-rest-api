package com.example.quiz_app.dto.quiz.management;

import com.example.quiz_app.enums.DifficultyLevel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuizManagementRequest {

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 255, message = "Title must be between 3 and 255 characters")
    private String title;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Difficulty level must be provided")
    private DifficultyLevel difficultyLevel;

    @NotNull(message = "Category ID must be provided")
    private int categoryId;

    @Valid
    @NotEmpty(message = "A quiz must have at least one question")
    private List<QuestionManagementRequest> questions;

}
