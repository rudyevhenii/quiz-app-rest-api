package com.example.quiz_app.mapper;

import com.example.quiz_app.dto.quiz.view.QuizHistoryResponse;
import com.example.quiz_app.model.Quiz;
import com.example.quiz_app.model.QuizHistory;
import org.springframework.stereotype.Component;

@Component
public class QuizHistoryMapper {

    public QuizHistoryResponse toResponse(QuizHistory quizHistory) {
        QuizHistoryResponse historyResponse = new QuizHistoryResponse();

        historyResponse.setId(quizHistory.getId());
        Quiz quiz = quizHistory.getQuiz();
        int score = quizHistory.getScore();
        int totalQuestions = quizHistory.getTotalQuestions();
        double percentage = ((double) score / totalQuestions) * 100;

        historyResponse.setQuizId(quiz.getId());
        historyResponse.setCategoryName(quiz.getCategory().getName());
        historyResponse.setDifficultyLevel(quiz.getDifficultyLevel().name());
        historyResponse.setScore(score);
        historyResponse.setTotalQuestions(totalQuestions);
        historyResponse.setPercentage(percentage);
        historyResponse.setSubmittedAt(quizHistory.getCreatedAt());

        return historyResponse;
    }

}
