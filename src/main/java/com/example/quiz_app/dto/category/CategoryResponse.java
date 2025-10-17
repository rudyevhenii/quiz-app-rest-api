package com.example.quiz_app.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CategoryResponse {

    private int id;
    private String name;

}
