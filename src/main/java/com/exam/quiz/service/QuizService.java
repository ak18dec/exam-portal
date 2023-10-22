package com.exam.quiz.service;

import com.exam.question.model.Question;
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

        Quiz quizOldData = quizRepository.findQuizById(quizId);

        System.out.println("Quiz fetched for id: "+quizId);
        System.out.println(quizOldData.toString());
        System.out.println("Quiz new data: ");
        System.out.println(quiz.toString());

        List<Integer> oldQuestions = quizOldData.getQuestionIds();
        List<Integer> newQuestions = quiz.getQuestionIds();

        if(oldQuestions != null && !oldQuestions.isEmpty()){
            boolean sameQuestions = oldQuestions.equals(newQuestions);
            if(!sameQuestions){
                quizRepository.updateQuizQuestions(quizId, newQuestions, 1);
            }
        }else {
            int[] newQuesIds = addQuizQuestions(newQuestions, quizId, 1);
        }

        return quizRepository.updateQuiz(quizId, quiz, 1);
    }

    public int[] addQuizQuestions(List<Integer> quesIds, int quizId, int loggedInUserId) {
        return quizRepository.addQuizQuestions(quesIds, loggedInUserId, quizId);
    }

    public List<Question> getQuestionsByQuizId(Integer quizId) {
        final List<Question> questions = quizRepository.findQuestionsByQuizId(quizId);
        return questions;
    }

}
