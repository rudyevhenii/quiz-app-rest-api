package com.example.quiz_app.service.impl;

import com.example.quiz_app.dto.category.CategoryCreateRequest;
import com.example.quiz_app.dto.category.CategoryResponse;
import com.example.quiz_app.dto.category.CategoryUpdateRequest;
import com.example.quiz_app.excpetion.ResourceAlreadyExistsException;
import com.example.quiz_app.excpetion.ResourceNotFoundException;
import com.example.quiz_app.mapper.CategoryMapper;
import com.example.quiz_app.model.Category;
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
    public CategoryResponse createCategory(CategoryCreateRequest request) {
        String name = request.getName();
        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new ResourceAlreadyExistsException("Category with name '" + name + "' already exists.");
        }
        Category newCategory = new Category();
        newCategory.setName(name);

        categoryRepository.save(newCategory);

        return categoryMapper.toResponse(newCategory);
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse findCategoryById(int id) {
        Category category = getCategoryById(id);

        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(int id, CategoryUpdateRequest request) {
        Category category = getCategoryById(id);

        String name = request.getName();
        category.setName(name);
        categoryRepository.save(category);

        return categoryMapper.toResponse(category);
    }

    @Override
    public void deleteCategory(int id) {
        Category category = getCategoryById(id);

        categoryRepository.delete(category);
    }

    private Category getCategoryById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

}
