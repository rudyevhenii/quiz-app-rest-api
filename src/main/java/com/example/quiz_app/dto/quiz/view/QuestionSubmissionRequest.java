package com.example.quiz_app.dto.quiz.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class QuestionSubmissionRequest {

    private int id;
    private Set<Integer> selectedOptionIds;

}
