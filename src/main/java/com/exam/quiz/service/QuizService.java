package com.exam.quiz.service;

import com.exam.quiz.exception.QuizNotFoundException;
import com.exam.quiz.model.Quiz;
import com.exam.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.exam.common.constant.ExceptionConstants.*;

import java.util.List;

@Service
public class QuizService {
    
    @Autowired
    private QuizRepository quizRepository;

    public List<Quiz> getQuizzes(){
        return quizRepository.findAll();
    }

    public Quiz addQuiz(Quiz quiz) {
        quizRepository.addQuiz(quiz, 1);
        return quiz;
    }

    public Quiz getQuizById(Integer quizId) throws QuizNotFoundException {
        final Quiz quiz =  quizRepository.findQuizById(quizId);
        if(quiz == null){
            throw new QuizNotFoundException(QUESTION_NOT_FOUND_FOR_ID+quizId);
        }
        return quiz;
    }

    public boolean removeQuiz(Integer quizId){
        return quizRepository.delete(quizId);
    }

    public boolean updateQuiz(Quiz quiz, Integer quizId){
        return quizRepository.updateQuiz(quizId, quiz, 1);
    }

}
