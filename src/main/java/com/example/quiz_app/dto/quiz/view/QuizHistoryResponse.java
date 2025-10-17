package com.example.quiz_app.dto.quiz.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class QuizHistoryResponse {

    private int id;
    private int quizId;
    private String quizTitle;
    private String categoryName;
    private String difficultyLevel;
    private int score;
    private int totalQuestions;
    private double percentage;
    private LocalDateTime submittedAt;

}
