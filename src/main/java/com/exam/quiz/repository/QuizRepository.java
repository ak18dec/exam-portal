package com.exam.quiz.repository;

import com.exam.quiz.model.Quiz;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
public class QuizRepository {
    
    public List<Quiz> findAll() {
        return Collections.emptyList();
    }

    @Transactional
    public int addQuiz(Quiz quiz, int loggedInUserId) {
        return -1;
    }

    @Transactional
    public boolean updateQuiz(int id, Quiz quiz, int loggedInUserId){
        return false;
    }

    public boolean delete(int id){
        return false;
    }

    public Quiz findQuizById(int id) {
        return null;
    }

}
