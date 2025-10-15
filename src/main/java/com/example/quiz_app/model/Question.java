package com.example.quiz_app.model;

import com.example.quiz_app.enums.QuestionType;
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

    @Column(name = "text", columnDefinition = "TEXT", nullable = false)
    private String text;

    @Column(name = "question_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MultipleChoiceOption> multipleChoiceOptions;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OpenTextAnswer> openTextAnswers;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;
        if (id == 0) return false;

        return getId() == question.getId();
    }

    @Override
    public int hashCode() {
        return id != 0 ? Integer.valueOf(id).hashCode() : getClass().hashCode();
    }

}
