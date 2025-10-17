package com.example.quiz_app.mapper;

import com.example.quiz_app.dto.category.CategoryResponse;
import com.example.quiz_app.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

}
