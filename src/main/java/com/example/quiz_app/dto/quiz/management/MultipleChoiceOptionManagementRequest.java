package com.example.quiz_app.dto.quiz.management;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MultipleChoiceOptionManagementRequest {

    private String optionText;
    private boolean correct;

}
