package com.example.quiz_app.dto.quiz.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OptionResultResponse {

    private int id;
    private String optionText;
    private boolean correct;
    private boolean userChoice;

}
