package com.example.quiz_app.repository;

import com.example.quiz_app.enums.UserRole;
import com.example.quiz_app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(UserRole name);

}
