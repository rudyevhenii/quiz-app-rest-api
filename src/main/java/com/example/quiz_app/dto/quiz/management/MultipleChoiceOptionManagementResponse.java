package com.example.quiz_app.dto.quiz.management;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MultipleChoiceOptionManagementResponse {

    private int id;
    private String optionText;
    private boolean correct;

}
