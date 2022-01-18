package com.exam.quiz.controller;

import com.exam.quiz.exception.QuizNotFoundException;
import com.exam.quiz.model.Quiz;
import com.exam.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;


    @GetMapping("/")
    public List<Quiz> getQuizzes(){
        return quizService.getQuizzes();
    }

    @PostMapping("/")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        return quizService.addQuiz(quiz);
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable("id") Integer quizId) throws QuizNotFoundException {
        return quizService.getQuizById(quizId);
    }

    @DeleteMapping("/{id}")
    public boolean removeQuiz(@PathVariable("id") Integer quizId){
        return quizService.removeQuiz(quizId);
    }

    @PutMapping("/{id}")
    public boolean updateQuiz(@RequestBody Quiz quiz, @PathVariable("id") Integer quizId){
        return quizService.updateQuiz(quiz, quizId);
    }

}
