package com.example.quiz_app.dto.quiz.view;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class QuestionSubmissionRequest {

    @Positive(message = "Question ID must be a positive number.")
    private int id;

    @NotNull(message = "Selected option IDs set cannot be null. Send an empty set if no option is selected.")
    private Set<Integer> selectedOptionIds;

}
