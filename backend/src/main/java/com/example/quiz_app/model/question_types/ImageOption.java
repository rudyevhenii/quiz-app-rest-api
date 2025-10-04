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
@Table(name = "image_options")
public class ImageOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "data", columnDefinition = "BLOB", nullable = false)
    private byte[] data;

    @Column(name = "is_correct", nullable = false)
    private boolean isCorrect;

}
