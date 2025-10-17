package com.example.quiz_app.service;

import com.example.quiz_app.dto.quiz.management.QuizManagementRequest;
import com.example.quiz_app.dto.quiz.management.QuizManagementResponse;
import com.example.quiz_app.dto.quiz.view.QuizHistoryResponse;
import com.example.quiz_app.dto.quiz.view.QuizResponse;
import com.example.quiz_app.dto.quiz.view.QuizResultResponse;
import com.example.quiz_app.dto.quiz.view.QuizSubmissionRequest;
import com.example.quiz_app.enums.DifficultyLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizService {

    // Methods for the admin or higher role access than user
    QuizManagementResponse createQuiz(String email, QuizManagementRequest quizRequest);

    QuizManagementResponse getQuizManagement(int quizId);

    Page<QuizManagementResponse> getAllQuizzesManagement(Pageable pageable);

    QuizManagementResponse updateQuiz(int quizId, QuizManagementRequest quizRequest);

    void deleteQuiz(int quizId);

    // Methods for the users
    Page<QuizResponse> getAllQuizzes(Integer categoryId, DifficultyLevel difficultyLevel, Pageable pageable);

    QuizResponse getQuiz(int quizId);

    QuizResultResponse submitQuiz(int quizId, String email, QuizSubmissionRequest answersRequest);

    Page<QuizHistoryResponse> getUserQuizHistory(String email, Pageable pageable);

}
