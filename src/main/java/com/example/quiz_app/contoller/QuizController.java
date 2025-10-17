package com.example.quiz_app.contoller;

import com.example.quiz_app.dto.quiz.view.QuizResponse;
import com.example.quiz_app.dto.quiz.view.QuizResultResponse;
import com.example.quiz_app.dto.quiz.view.QuizSubmissionRequest;
import com.example.quiz_app.enums.DifficultyLevel;
import com.example.quiz_app.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public ResponseEntity<Page<QuizResponse>> getAllQuizzes(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) DifficultyLevel difficultyLevel,
            Pageable pageable
    ) {
        Page<QuizResponse> responsePage = quizService.getAllQuizzes(categoryId, difficultyLevel, pageable);

        return ResponseEntity.ok(responsePage);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizResponse> getQuiz(@PathVariable int quizId) {
        QuizResponse response = quizService.getQuiz(quizId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{quizId}/submit")
    public ResponseEntity<QuizResultResponse> submitQuiz(
            Authentication authentication,
            @PathVariable int quizId,
            @RequestBody QuizSubmissionRequest request
    ) {
        String email = authentication.getName();
        QuizResultResponse response = quizService.submitQuiz(quizId, email, request);

        return ResponseEntity.ok(response);
    }

}
