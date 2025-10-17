package com.example.quiz_app.dto.quiz.management;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionManagementResponse {

    private int id;
    private String text;
    private List<MultipleChoiceOptionManagementResponse> options;

}
