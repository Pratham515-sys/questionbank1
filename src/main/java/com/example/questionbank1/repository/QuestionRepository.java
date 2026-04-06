package com.example.questionbank1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.questionbank1.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByDifficultyIgnoreCase(String difficulty);

    List<Question> findBySubjectIgnoreCase(String subject);

    List<Question> findByUnitIgnoreCase(String unit);

    List<Question> findByFacultyIgnoreCase(String faculty);
}
