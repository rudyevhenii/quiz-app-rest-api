package com.example.quiz_app.repository;

import com.example.quiz_app.enums.DifficultyLevel;
import com.example.quiz_app.model.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    Page<Quiz> findByCategoryIdAndDifficultyLevel(Integer categoryId, DifficultyLevel difficulty, Pageable pageable);

    Page<Quiz> findByCategoryId(Integer categoryId, Pageable pageable);

    Page<Quiz> findByDifficultyLevel(DifficultyLevel difficulty, Pageable pageable);

}
