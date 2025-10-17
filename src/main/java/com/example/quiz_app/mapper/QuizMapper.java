package com.example.quiz_app.mapper;

import com.example.quiz_app.dto.quiz.management.MultipleChoiceOptionManagementResponse;
import com.example.quiz_app.dto.quiz.management.QuestionManagementResponse;
import com.example.quiz_app.dto.quiz.management.QuizManagementResponse;
import com.example.quiz_app.dto.quiz.view.MultipleChoiceOptionResponse;
import com.example.quiz_app.dto.quiz.view.QuestionResponse;
import com.example.quiz_app.dto.quiz.view.QuizResponse;
import com.example.quiz_app.model.Question;
import com.example.quiz_app.model.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizMapper {

    public QuizManagementResponse toManagementResponse(Quiz quiz) {
        QuizManagementResponse quizResponse = new QuizManagementResponse();
        quizResponse.setId(quiz.getId());
        quizResponse.setTitle(quiz.getTitle());
        quizResponse.setDescription(quiz.getDescription());
        quizResponse.setDifficultyLevel(quiz.getDifficultyLevel());
        quizResponse.setCategoryId(quiz.getCategory().getId());

        List<QuestionManagementResponse> questionResponses = new ArrayList<>();

        for (var q : quiz.getQuestions()) {
            QuestionManagementResponse question = getQuestionManagementResponse(q);
            questionResponses.add(question);
        }
        quizResponse.setQuestions(questionResponses);

        return quizResponse;
    }

    private QuestionManagementResponse getQuestionManagementResponse(Question q) {
        QuestionManagementResponse question = new QuestionManagementResponse();
        question.setId(q.getId());
        question.setText(q.getText());

        List<MultipleChoiceOptionManagementResponse> options = new ArrayList<>();
        for (var m : q.getMultipleChoiceOptions()) {
            MultipleChoiceOptionManagementResponse option = new MultipleChoiceOptionManagementResponse();
            option.setId(m.getId());
            option.setOptionText(m.getOptionText());
            option.setCorrect(m.isCorrect());

            options.add(option);
        }

        question.setOptions(options);
        return question;
    }

    public QuizResponse toResponse(Quiz quiz) {
        QuizResponse quizResponse = new QuizResponse();
        quizResponse.setId(quiz.getId());
        quizResponse.setTitle(quiz.getTitle());
        quizResponse.setDescription(quiz.getDescription());
        quizResponse.setDifficultyLevel(quiz.getDifficultyLevel());
        quizResponse.setCategoryId(quiz.getCategory().getId());

        List<QuestionResponse> questionResponses = new ArrayList<>();

        for (var q : quiz.getQuestions()) {
            QuestionResponse question = getQuestionResponse(q);
            questionResponses.add(question);
        }
        quizResponse.setQuestions(questionResponses);

        return quizResponse;
    }

    private QuestionResponse getQuestionResponse(Question q) {
        QuestionResponse question = new QuestionResponse();
        question.setId(q.getId());
        question.setText(q.getText());

        List<MultipleChoiceOptionResponse> options = new ArrayList<>();
        for (var m : q.getMultipleChoiceOptions()) {
            MultipleChoiceOptionResponse option = new MultipleChoiceOptionResponse();
            option.setId(m.getId());
            option.setOptionText(m.getOptionText());

            options.add(option);
        }

        question.setOptions(options);
        return question;
    }

}
