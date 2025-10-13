package com.example.quiz_app.service;

import com.example.quiz_app.dto.request.CategoryCreateRequest;
import com.example.quiz_app.dto.request.CategoryUpdateRequest;
import com.example.quiz_app.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryCreateRequest request);

    List<CategoryResponse> findAllCategories();

    CategoryResponse findCategoryById(int id);

    CategoryResponse updateCategory(int id, CategoryUpdateRequest request);

    void deleteCategory(int id);

}
