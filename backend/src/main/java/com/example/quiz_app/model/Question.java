package com.example.quiz_app.model;

import com.example.quiz_app.enums.DifficultyLevel;
import com.example.quiz_app.enums.QuestionType;
import com.example.quiz_app.model.question_types.ImageOption;
import com.example.quiz_app.model.question_types.MultipleChoiceOption;
import com.example.quiz_app.model.question_types.OpenTextAnswer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MultipleChoiceOption> multipleChoiceOptions;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<ImageOption> imageOptions;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<OpenTextAnswer> openTextAnswers;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType questionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level")
    private DifficultyLevel difficultyLevel;

}
