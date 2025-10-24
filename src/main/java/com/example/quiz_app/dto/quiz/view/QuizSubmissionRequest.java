package com.example.quiz_app.dto.quiz.view;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuizSubmissionRequest {

    @Valid
    @NotEmpty(message = "Submission must contain at least one answer.")
    private List<QuestionSubmissionRequest> answers;

}
