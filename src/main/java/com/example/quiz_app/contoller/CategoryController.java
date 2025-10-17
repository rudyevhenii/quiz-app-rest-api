package com.example.quiz_app.contoller;

import com.example.quiz_app.dto.category.CategoryCreateRequest;
import com.example.quiz_app.dto.category.CategoryResponse;
import com.example.quiz_app.dto.category.CategoryUpdateRequest;
import com.example.quiz_app.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> categories() {
        List<CategoryResponse> categories = categoryService.findAllCategories();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createNewCategory(
            @Valid @RequestBody CategoryCreateRequest request
    ) {
        CategoryResponse response = categoryService.createCategory(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findCategory(@PathVariable int id) {
        CategoryResponse response = categoryService.findCategoryById(id);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable int id,
            @Valid @RequestBody CategoryUpdateRequest request
    ) {
        CategoryResponse response = categoryService.updateCategory(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.noContent()
                .build();
    }

}
