package com.example.quiz_app.dto.quiz.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResultResponse {

    private int id;
    private String text;
    private boolean answeredCorrectly;
    private List<OptionResultResponse> options;

}
