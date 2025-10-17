package com.example.quiz_app.dto.quiz.management;

import com.example.quiz_app.enums.DifficultyLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuizManagementResponse {

    private int id;
    private String title;
    private String description;
    private DifficultyLevel difficultyLevel;
    private int categoryId;
    private List<QuestionManagementResponse> questions;

}
