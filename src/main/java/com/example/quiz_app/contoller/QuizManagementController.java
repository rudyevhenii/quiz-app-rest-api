package com.example.quiz_app.contoller;

import com.example.quiz_app.dto.quiz.management.QuizManagementRequest;
import com.example.quiz_app.dto.quiz.management.QuizManagementResponse;
import com.example.quiz_app.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management/quizzes")
@RequiredArgsConstructor
public class QuizManagementController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizManagementResponse> createQuiz(
            Authentication authentication,
            @RequestBody QuizManagementRequest quizRequest
    ) {
        String email = authentication.getName();
        QuizManagementResponse response = quizService.createQuiz(email, quizRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizManagementResponse> getQuizManagement(@PathVariable int quizId) {
        QuizManagementResponse response = quizService.getQuizManagement(quizId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<QuizManagementResponse>> getAllQuizzesManagement(Pageable pageable) {
        Page<QuizManagementResponse> response = quizService.getAllQuizzesManagement(pageable);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<QuizManagementResponse> updateQuiz(
            @PathVariable int quizId,
            @RequestBody QuizManagementRequest quizRequest
    ) {
        QuizManagementResponse response = quizService.updateQuiz(quizId, quizRequest);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable int quizId) {
        quizService.deleteQuiz(quizId);

        return ResponseEntity.noContent()
                .build();
    }

}
