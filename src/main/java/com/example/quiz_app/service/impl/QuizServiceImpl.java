package com.example.quiz_app.service.impl;

import com.example.quiz_app.dto.quiz.management.QuizManagementRequest;
import com.example.quiz_app.dto.quiz.management.QuizManagementResponse;
import com.example.quiz_app.dto.quiz.view.*;
import com.example.quiz_app.enums.DifficultyLevel;
import com.example.quiz_app.mapper.QuizHistoryMapper;
import com.example.quiz_app.mapper.QuizMapper;
import com.example.quiz_app.model.*;
import com.example.quiz_app.repository.CategoryRepository;
import com.example.quiz_app.repository.QuizHistoryRepository;
import com.example.quiz_app.repository.QuizRepository;
import com.example.quiz_app.repository.UserRepository;
import com.example.quiz_app.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final QuizHistoryRepository quizHistoryRepository;
    private final QuizMapper quizMapper;
    private final QuizHistoryMapper quizHistoryMapper;

    @Override
    @Transactional
    public QuizManagementResponse createQuiz(String email, QuizManagementRequest quizRequest) {
        User user = getUserById(email);

        Category category = getCategoryById(quizRequest.getCategoryId());

        Quiz quiz = new Quiz();
        quiz.setTitle(quizRequest.getTitle());
        quiz.setDescription(quizRequest.getDescription());
        quiz.setDifficultyLevel(quizRequest.getDifficultyLevel());
        quiz.setCategory(category);
        quiz.setUser(user);

        addQuestionsToQuiz(quizRequest, quiz);
        Quiz savedQuiz = quizRepository.save(quiz);

        return quizMapper.toManagementResponse(savedQuiz);
    }

    @Override
    @Transactional(readOnly = true)
    public QuizManagementResponse getQuizManagement(int quizId) {
        Quiz quiz = getQuizById(quizId);

        return quizMapper.toManagementResponse(quiz);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuizManagementResponse> getAllQuizzesManagement(Pageable pageable) {
        Page<Quiz> quizzes = quizRepository.findAll(pageable);

        return quizzes.map(quizMapper::toManagementResponse);
    }

    @Override
    @Transactional
    public QuizManagementResponse updateQuiz(int quizId, QuizManagementRequest quizRequest) {
        Quiz quiz = getQuizById(quizId);

        quiz.setTitle(quizRequest.getTitle());
        quiz.setDescription(quizRequest.getDescription());
        quiz.setDifficultyLevel(quizRequest.getDifficultyLevel());

        quiz.getQuestions().clear();

        addQuestionsToQuiz(quizRequest, quiz);
        Quiz savedQuiz = quizRepository.save(quiz);

        return quizMapper.toManagementResponse(savedQuiz);
    }

    @Override
    @Transactional
    public void deleteQuiz(int quizId) {
        Quiz quiz = getQuizById(quizId);

        quizRepository.delete(quiz);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuizResponse> getAllQuizzes(Integer categoryId, DifficultyLevel difficultyLevel,
                                            Pageable pageable) {
        Page<Quiz> quizPage;

        if (categoryId != null && difficultyLevel != null) {
            quizPage = quizRepository.findByCategoryIdAndDifficultyLevel(categoryId, difficultyLevel, pageable);
        } else if (categoryId != null) {
            quizPage = quizRepository.findByCategoryId(categoryId, pageable);
        } else if (difficultyLevel != null) {
            quizPage = quizRepository.findByDifficultyLevel(difficultyLevel, pageable);
        } else {
            quizPage = quizRepository.findAll(pageable);
        }
        return quizPage.map(quizMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public QuizResponse getQuiz(int quizId) {
        Quiz quiz = getQuizById(quizId);

        return quizMapper.toResponse(quiz);
    }

    @Override
    @Transactional
    public QuizResultResponse submitQuiz(int quizId, String email, QuizSubmissionRequest answersRequest) {
        Quiz quiz = getQuizById(quizId);
        User user = getUserById(email);

        Map<Integer, Set<Integer>> userAnswers = answersRequest.getAnswers().stream()
                .collect(Collectors.toMap(QuestionSubmissionRequest::getId,
                        QuestionSubmissionRequest::getSelectedOptionIds));

        List<QuestionResultResponse> detailedQuestionResponse = new ArrayList<>();
        int score = 0;

        for (Question question : quiz.getQuestions()) {
            Set<Integer> correctOptionIds = question.getMultipleChoiceOptions().stream()
                    .filter(MultipleChoiceOption::isCorrect)
                    .map(MultipleChoiceOption::getId)
                    .collect(Collectors.toSet());

            Set<Integer> selectedOptionIds = userAnswers.getOrDefault(question.getId(), Collections.emptySet());

            boolean isCorrectlyAnswered = correctOptionIds.equals(selectedOptionIds);
            if (isCorrectlyAnswered) {
                score++;
            }
            detailedQuestionResponse.add(buildQuestionResultResponse(question, selectedOptionIds, isCorrectlyAnswered));
        }
        int totalQuestions = quiz.getQuestions().size();
        QuizHistory quizHistory = QuizHistory.builder()
                .score(score)
                .totalQuestions(totalQuestions)
                .user(user)
                .quiz(quiz)
                .build();
        QuizHistory savedQuizHistory = quizHistoryRepository.save(quizHistory);

        double percentage = (double) score / totalQuestions * 100;

        return new QuizResultResponse(
                savedQuizHistory.getId(),
                quiz.getTitle(),
                quiz.getId(),
                score,
                totalQuestions,
                percentage,
                savedQuizHistory.getCreatedAt(),
                detailedQuestionResponse
        );
    }

    private QuestionResultResponse buildQuestionResultResponse(Question question, Set<Integer> selectedOptionIds,
                                                               boolean isCorrectlyAnswered) {
        List<OptionResultResponse> optionResults = question.getMultipleChoiceOptions().stream()
                .map(option -> new OptionResultResponse(
                        option.getId(),
                        option.getOptionText(),
                        option.isCorrect(),
                        selectedOptionIds.contains(option.getId())
                ))
                .toList();

        return new QuestionResultResponse(question.getId(), question.getText(),
                isCorrectlyAnswered, optionResults);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuizHistoryResponse> getUserQuizHistory(String email, Pageable pageable) {
        Page<QuizHistory> resultsPage = quizHistoryRepository.findByUserEmail(email, pageable);

        return resultsPage.map(quizHistoryMapper::toResponse);
    }

    private void addQuestionsToQuiz(QuizManagementRequest quizRequest, Quiz quiz) {
        for (var q : quizRequest.getQuestions()) {
            Question question = new Question();
            question.setText(q.getText());
            question.setQuiz(quiz);

            for (var m : q.getOptions()) {
                MultipleChoiceOption option = new MultipleChoiceOption();
                option.setOptionText(m.getOptionText());
                option.setCorrect(m.isCorrect());

                option.setQuestion(question);
                question.getMultipleChoiceOptions().add(option);
            }
            quiz.getQuestions().add(question);
        }
    }

    private User getUserById(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User is not found with email: " + email));
    }

    private Category getCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category is not found with id: " + categoryId));
    }

    private Quiz getQuizById(int quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz is not found with id: " + quizId));
    }

}
