package com.example.quiz_app.service.impl;

import com.example.quiz_app.dto.response.CategoryResponse;
import com.example.quiz_app.mapper.CategoryMapper;
import com.example.quiz_app.repository.CategoryRepository;
import com.example.quiz_app.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

}
