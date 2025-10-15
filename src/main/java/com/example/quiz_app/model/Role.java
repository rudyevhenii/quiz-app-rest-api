package com.example.quiz_app.model;

import com.example.quiz_app.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private UserRole name;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

}
