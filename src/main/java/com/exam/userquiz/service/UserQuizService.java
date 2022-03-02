package com.exam.userquiz.service;

import com.exam.question.model.Question;
import com.exam.question.model.QuestionChoice;
import com.exam.quiz.service.QuizService;
import com.exam.userquiz.model.AttemptedQuizQuestion;
import com.exam.userquiz.model.UserAttemptedQuiz;
import com.exam.userquiz.repository.UserQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserQuizService {

    @Autowired
    UserQuizRepository userQuizRepository;

    @Autowired
    QuizService quizService;

    public Double submitQuiz(UserAttemptedQuiz quiz) {
        double score = evaluateQuiz(quiz);
        quiz.setScore(score);
//        userQuizRepository.submitQuiz(quiz); //can be made asynchronous multi-threaded call
        return score;
    }

    private Double evaluateQuiz(UserAttemptedQuiz quiz) {
        double score = 0.0;
        final double eachQuestionWeightage = (double) quiz.getMaxMarks() / quiz.getMaxTime();
        final List<Question> questions = quizService.getQuestionsByQuizId(quiz.getId());

        Map<Integer, Question> quesMap = new HashMap<>();
        questions.forEach(ques -> quesMap.put(ques.getId(), ques));

        final List<AttemptedQuizQuestion> submittedQuestions = quiz.getQuiz().getQuestions();

        for (AttemptedQuizQuestion submittedQuestion : submittedQuestions) {
            Question question = quesMap.get(submittedQuestion.getId());
            String correctAnswer = question.getCorrectChoice();
            if(correctAnswer.equals(submittedQuestion.getOptionSelected())){
                score += eachQuestionWeightage;
            }
        }

        return score;
    }

}
