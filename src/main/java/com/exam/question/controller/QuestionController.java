package com.exam.question.controller;

import com.exam.question.exception.QuestionAlreadyExistsException;
import com.exam.question.exception.QuestionNotFoundException;
import com.exam.question.model.Question;
import com.exam.question.model.QuestionChoice;
import com.exam.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public List<Question> getQuestions(){
        return questionService.getQuestions();
    }

    @PostMapping("/")
    public Question addQuestion(@RequestBody Question question) throws QuestionAlreadyExistsException {
        return questionService.addQuestion(question, 1);
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable("id") Integer questionId) throws QuestionNotFoundException {
        return questionService.getQuestionById(questionId);
    }

    @DeleteMapping("/{id}")
    public boolean removeQuestion(@PathVariable("id") Integer questionId){
        return questionService.removeQuestion(questionId);
    }

    @PutMapping("/{id}")
    public boolean updateQuestion(@RequestBody Question question, @PathVariable("id") Integer id){
        return questionService.updateQuestion(id, question, 1);
    }

    @PutMapping("/{id}/choices/")
    public boolean updateQuestionChoicesByQuestionId(@RequestBody List<QuestionChoice> choices, @PathVariable("id") Integer id){
        return questionService.updateQuestionChoicesByQuestionId(id, choices, 1);
    }
}
