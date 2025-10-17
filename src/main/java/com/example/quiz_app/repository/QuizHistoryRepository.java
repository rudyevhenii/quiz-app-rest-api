package com.example.quiz_app.repository;

import com.example.quiz_app.model.QuizHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizHistoryRepository extends JpaRepository<QuizHistory, Integer> {

    Page<QuizHistory> findByUserEmail(String email, Pageable pageable);

}
