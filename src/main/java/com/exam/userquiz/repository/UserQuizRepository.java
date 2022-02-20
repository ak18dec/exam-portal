package com.exam.userquiz.repository;

import com.exam.userquiz.model.UserAttemptedQuiz;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserQuizRepository {

    @Transactional
    public int submitQiuz(UserAttemptedQuiz quiz) {
        return -1;
    }
}
