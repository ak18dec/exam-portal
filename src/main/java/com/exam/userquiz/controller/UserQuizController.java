package com.exam.userquiz.controller;

import com.exam.userquiz.model.UserAttemptedQuiz;
import com.exam.userquiz.service.UserQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-quiz")
public class UserQuizController {

    @Autowired
    UserQuizService userQuizService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitQuiz(@RequestBody UserAttemptedQuiz quiz) {
        return ResponseEntity.ok(userQuizService.submitQuiz(quiz));
    }
}
