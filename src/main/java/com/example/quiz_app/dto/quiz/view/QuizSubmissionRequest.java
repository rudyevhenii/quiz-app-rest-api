package com.example.quiz_app.dto.quiz.view;

import com.example.quiz_app.enums.DifficultyLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuizSubmissionRequest {

    private List<QuestionSubmissionRequest> answers;

}
