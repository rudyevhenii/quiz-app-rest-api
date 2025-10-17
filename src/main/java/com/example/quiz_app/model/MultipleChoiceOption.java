package com.example.quiz_app.model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MultipleChoiceOption that = (MultipleChoiceOption) o;
        if (id == 0) return false;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return id != 0 ? Integer.valueOf(id).hashCode() : getClass().hashCode();
    }

}
