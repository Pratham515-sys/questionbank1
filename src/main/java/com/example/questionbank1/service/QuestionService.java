package com.example.questionbank1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.questionbank1.entity.Question;
import com.example.questionbank1.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repo;

    // ✅ Add Question
    public Question addQuestion(Question q) {
        return repo.save(q);
    }

    // ✅ Get All Questions
    public List<Question> getAllQuestions() {
        return repo.findAll();
    }

    // ✅ Get by Difficulty
    public List<Question> getByDifficulty(String difficulty) {
        return repo.findByDifficultyIgnoreCase(difficulty);
    }

    // ✅ Get Question by ID
    public Question getQuestionById(Long id) {
        return repo.findById(id).orElseThrow(
            () -> new RuntimeException("Question not found: " + id)
        );
    }

    // ✅ Update Question
    public Question updateQuestion(Long id, Question updated) {
        return repo.findById(id).map(q -> {
            q.setQuestionTitle(updated.getQuestionTitle());
            q.setOption1(updated.getOption1());
            q.setOption2(updated.getOption2());
            q.setOption3(updated.getOption3());
            q.setOption4(updated.getOption4());
            q.setCorrectAnswer(updated.getCorrectAnswer());
            q.setDifficulty(updated.getDifficulty());
            q.setSubject(updated.getSubject());
            q.setUnit(updated.getUnit());
            q.setFaculty(updated.getFaculty());
            return repo.save(q);
        }).orElseThrow(() -> new RuntimeException("Question not found: " + id));
    }

    // ✅ Get by Subject
    public List<Question> getBySubject(String subject) {
        return repo.findBySubjectIgnoreCase(subject);
    }

    // ✅ Get by Unit
    public List<Question> getByUnit(String unit) {
        return repo.findByUnitIgnoreCase(unit);
    }

    // ✅ Get by Faculty
    public List<Question> getByFaculty(String faculty) {
        return repo.findByFacultyIgnoreCase(faculty);
    }

    // ✅ Delete Question
    public void deleteQuestion(Long id) {
        repo.deleteById(id);
    }

    // ✅ Generate Paper (3 easy, 3 medium, 3 hard)
    public List<Question> generatePaper() {

        List<Question> result = new ArrayList<>();

        result.addAll(getRandomQuestions("easy", 3));
        result.addAll(getRandomQuestions("medium", 3));
        result.addAll(getRandomQuestions("hard", 3));

        return result;
    }

    // 🔥 Helper Method
    private List<Question> getRandomQuestions(String difficulty, int count) {

        List<Question> list = repo.findByDifficultyIgnoreCase(difficulty);

        List<Question> selected = new ArrayList<>();

        for (int i = 0; i < count && i < list.size(); i++) {
            selected.add(list.get(i)); // simple selection (can randomize later)
        }

        return selected;
    }
}