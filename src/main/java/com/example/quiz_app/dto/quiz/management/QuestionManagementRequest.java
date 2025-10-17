package com.example.quiz_app.dto.quiz.management;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionManagementRequest {

    private String text;
    private List<MultipleChoiceOptionManagementRequest> options;

}
