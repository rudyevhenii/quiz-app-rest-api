package com.example.quiz_app.model.question_types;

import com.example.quiz_app.model.Question;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "multiple_choice_options")
public class MultipleChoiceOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "option_text", nullable = false)
    private String optionText;

    @Column(name = "is_correct", nullable = false)
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

}
