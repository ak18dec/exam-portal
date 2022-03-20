package com.exam.userquiz.service;

import com.exam.question.model.Question;
import com.exam.quiz.service.QuizService;
import com.exam.userquiz.model.AttemptedQuizQuestion;
import com.exam.userquiz.model.ScoreDetails;
import com.exam.userquiz.model.UserAttemptedQuiz;
import com.exam.userquiz.repository.UserQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserQuizService {

    @Autowired
    UserQuizRepository userQuizRepository;

    @Autowired
    QuizService quizService;

    public ScoreDetails submitQuiz(UserAttemptedQuiz quiz) {
        ScoreDetails scoreDetails = evaluateQuiz(quiz);
        quiz.setScore(scoreDetails.getScore());
//        userQuizRepository.submitQuiz(quiz); //can be made asynchronous multi-threaded call
        return scoreDetails;
    }

    private ScoreDetails evaluateQuiz(UserAttemptedQuiz quiz) {
        double score = 0.0;
        int correctQuestions = 0;
        int incorrectQuestions = 0;
        int totalQuestions = 0;
        int totalAttemptedQuestions = 0;
        final List<Question> questions = quizService.getQuestionsByQuizId(quiz.getQuiz().getId());
        totalQuestions = questions.size();
        final double eachQuestionWeightage = (double) quiz.getMaxMarks() / totalQuestions;

        Map<Integer, Question> quesMap = new HashMap<>();
        questions.forEach(ques -> quesMap.put(ques.getId(), ques));

        final List<AttemptedQuizQuestion> submittedQuestions = quiz.getQuiz().getQuestions();

        for (AttemptedQuizQuestion submittedQuestion : submittedQuestions) {
            Question question = quesMap.get(submittedQuestion.getId());
            String correctAnswer = question.getCorrectChoice();
            if(correctAnswer.equals(submittedQuestion.getOptionSelected())){
                score += eachQuestionWeightage;
                correctQuestions++;
            }
        }

        totalAttemptedQuestions = submittedQuestions.size();
        incorrectQuestions = totalAttemptedQuestions - correctQuestions;

        ScoreDetails scoreDetails = new ScoreDetails();
        scoreDetails.setScore(score);
        scoreDetails.setCorrectQuestions(correctQuestions);
        scoreDetails.setIncorrectQuestions(incorrectQuestions);
        scoreDetails.setTotalAttemptedQuestions(totalAttemptedQuestions);
        scoreDetails.setTotalQuestions(totalQuestions);
        scoreDetails.setCertificateId(generateCertificateId());

        return scoreDetails;
    }

    private String generateCertificateId(){
        return UUID.randomUUID().toString();
    }

}
