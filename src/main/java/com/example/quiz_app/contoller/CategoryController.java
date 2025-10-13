package com.example.quiz_app.contoller;

import com.example.quiz_app.dto.response.CategoryResponse;
import com.example.quiz_app.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> categories() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

}
