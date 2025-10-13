package com.example.quiz_app.service;

import com.example.quiz_app.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAllCategories();

}
