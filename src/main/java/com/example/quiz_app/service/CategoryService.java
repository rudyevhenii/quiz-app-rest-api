package com.example.quiz_app.service;

import com.example.quiz_app.dto.category.CategoryCreateRequest;
import com.example.quiz_app.dto.category.CategoryResponse;
import com.example.quiz_app.dto.category.CategoryUpdateRequest;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryCreateRequest request);

    List<CategoryResponse> findAllCategories();

    CategoryResponse findCategoryById(int id);

    CategoryResponse updateCategory(int id, CategoryUpdateRequest request);

    void deleteCategory(int id);

}
