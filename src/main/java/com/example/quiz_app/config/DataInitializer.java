package com.example.quiz_app.config;

import com.example.quiz_app.enums.UserRole;
import com.example.quiz_app.model.Category;
import com.example.quiz_app.model.Profile;
import com.example.quiz_app.model.Role;
import com.example.quiz_app.model.User;
import com.example.quiz_app.repository.CategoryRepository;
import com.example.quiz_app.repository.ProfileRepository;
import com.example.quiz_app.repository.RoleRepository;
import com.example.quiz_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role userRole = Role.builder().name(UserRole.ROLE_USER).build();
            Role adminRole = Role.builder().name(UserRole.ROLE_ADMIN).build();
            roleRepository.saveAll(List.of(userRole, adminRole));
        }

        if (categoryRepository.count() == 0) {
            List<String> categoryNames = List.of(
                    "Science", "History", "Geography", "Mathematics", "Technology",
                    "Sports", "Art", "Music", "Movies", "Literature", "Animals",
                    "Food", "General Knowledge"
            );
            List<Category> categories = categoryNames.stream()
                    .map(name -> Category.builder().name(name).build())
                    .collect(Collectors.toList());
            categoryRepository.saveAll(categories);
        }

        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByName(UserRole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Admin role is not found."));

            User adminUser = User.builder()
                    .email("admin1@gmail.com")
                    .password(passwordEncoder.encode("admin1"))
                    .role(adminRole)
                    .build();

            Profile adminProfile = Profile.builder()
                    .firstName("Admin")
                    .lastName("User")
                    .dateOfBirth(LocalDate.of(1990, 1, 1))
                    .user(adminUser)
                    .build();

            adminUser.setProfile(adminProfile);

            userRepository.save(adminUser);
        }
    }

}
