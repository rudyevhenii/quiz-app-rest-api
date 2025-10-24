package com.example.quiz_app.dto.quiz.management;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionManagementRequest {

    @NotBlank(message = "Question text cannot be empty")
    private String text;

    @Valid
    @NotEmpty(message = "Question must have at least one option")
    private List<MultipleChoiceOptionManagementRequest> options;

}
