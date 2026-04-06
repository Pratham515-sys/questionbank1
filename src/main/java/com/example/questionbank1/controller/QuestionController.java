package com.example.questionbank1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.questionbank1.entity.Question;
import com.example.questionbank1.service.QuestionService;

@RestController
@RequestMapping("/questions")
@CrossOrigin(origins = "*")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @GetMapping
    public List<Question> getAll() {
        return service.getAllQuestions();
    }

    @GetMapping("/generate")
    public List<Question> generatePaper() {
        return service.generatePaper();
    }

    @GetMapping("/difficulty/{level}")
    public List<Question> getByDifficulty(@PathVariable String level) {
        return service.getByDifficulty(level);
    }

    @GetMapping("/subject/{subject}")
    public List<Question> getBySubject(@PathVariable String subject) {
        return service.getBySubject(subject);
    }

    @GetMapping("/unit/{unit}")
    public List<Question> getByUnit(@PathVariable String unit) {
        return service.getByUnit(unit);
    }

    @GetMapping("/faculty/{faculty}")
    public List<Question> getByFaculty(@PathVariable String faculty) {
        return service.getByFaculty(faculty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getQuestionById(id));
    }

    @PostMapping
    public Question addQuestion(@RequestBody Question q) {
        return service.addQuestion(q);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> update(@PathVariable Long id, @RequestBody Question q) {
        return ResponseEntity.ok(service.updateQuestion(id, q));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteQuestion(id);
        return "Deleted successfully";
    }
}
