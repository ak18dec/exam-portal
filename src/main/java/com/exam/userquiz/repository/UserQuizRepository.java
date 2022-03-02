package com.exam.userquiz.repository;

import com.exam.userquiz.model.UserAttemptedQuiz;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserQuizRepository {

    @Transactional
    public int submitQuiz(UserAttemptedQuiz quiz) {
        return -1;
    }
}
