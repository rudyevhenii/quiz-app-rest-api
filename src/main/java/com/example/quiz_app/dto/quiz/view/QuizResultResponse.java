package com.example.quiz_app.dto.quiz.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizResultResponse {

    private int id;
    private String title;
    private int quizId;
    private int score;
    private int totalQuestions;
    private double percentage;
    private LocalDateTime submittedAt;
    private List<QuestionResultResponse> questions;

}
