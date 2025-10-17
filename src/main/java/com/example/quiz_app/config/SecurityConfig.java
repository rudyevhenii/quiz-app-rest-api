package com.example.quiz_app.config;

import com.example.quiz_app.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String API_BASE_PATH = "/api/v1";

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                API_BASE_PATH + "/auth/login",
                                API_BASE_PATH + "/auth/register"
                        ).permitAll()
                        .requestMatchers(API_BASE_PATH + "/auth/refresh-token").authenticated()
                        .requestMatchers(API_BASE_PATH + "/profile/**").authenticated()
                        .requestMatchers(API_BASE_PATH + "/categories").authenticated()
                        .requestMatchers(API_BASE_PATH + "/categories/**").hasRole("ADMIN")
                        .requestMatchers(API_BASE_PATH + "/quizzes/**").authenticated()
                        .requestMatchers(API_BASE_PATH + "/management/quizzes/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
