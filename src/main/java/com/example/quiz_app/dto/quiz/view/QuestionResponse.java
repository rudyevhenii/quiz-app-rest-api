package com.example.quiz_app.dto.quiz.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionResponse {

    private int id;
    private String text;
    private List<MultipleChoiceOptionResponse> options;

}
