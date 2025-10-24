package com.example.quiz_app.dto.quiz.management;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MultipleChoiceOptionManagementRequest {

    @NotBlank
    private String optionText;

    @NotNull
    private boolean correct;

}
