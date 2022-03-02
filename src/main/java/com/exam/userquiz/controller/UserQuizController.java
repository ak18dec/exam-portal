package com.exam.userquiz.controller;

import com.exam.userquiz.model.UserAttemptedQuiz;
import com.exam.userquiz.service.UserQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-quiz")
@CrossOrigin("*")
public class UserQuizController {

    @Autowired
    UserQuizService userQuizService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitQuiz(@RequestBody UserAttemptedQuiz quiz) {
        return ResponseEntity.ok(userQuizService.submitQuiz(quiz));
    }
}
