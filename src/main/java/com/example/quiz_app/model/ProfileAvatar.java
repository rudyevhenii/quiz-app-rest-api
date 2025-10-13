package com.example.quiz_app.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "profile_avatars")
public class ProfileAvatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @Column(name = "content_type")
    private String contentType;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
