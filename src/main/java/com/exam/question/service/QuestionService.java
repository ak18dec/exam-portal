package com.exam.question.service;

import com.exam.question.exception.QuestionAlreadyExistsException;
import com.exam.question.exception.QuestionNotFoundException;
import com.exam.question.model.Question;
import com.exam.question.model.QuestionChoice;
import com.exam.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exam.common.constant.ExceptionConstants.*;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }

    public Question addQuestion(Question question, int loggedInUserId) throws QuestionAlreadyExistsException {
        final boolean questionExistWithContent = questionRepository.questionExistsByTitle(question.getContent());

        if(questionExistWithContent){
            throw new QuestionAlreadyExistsException(QUESTION_ALREADY_EXISTS+question.getContent());
        }else{
            int newQuestionId = questionRepository.addQuestion(question, loggedInUserId);
            question.setId(newQuestionId);
        }

        return question;
    }

    public Question getQuestionById(Integer questionId) throws QuestionNotFoundException {
        final Question question = questionRepository.findById(questionId);
        if(question == null) {
            throw new QuestionNotFoundException(QUESTION_NOT_FOUND_FOR_ID+questionId);
        }
        return question;
    }

    public Question getQuestionByTitle(String title) throws QuestionNotFoundException {
        final Question question = questionRepository.findByTitle(title);
        if(question == null) {
            throw new QuestionNotFoundException(QUESTION_NOT_FOUND_FOR_TITLE+title);
        }
        return question;
    }

    public boolean removeQuestion(Integer questionId){
        return questionRepository.delete(questionId);
    }

    public boolean removeAllQuestions(){
        return questionRepository.deleteAll();
    }

    public boolean removeQuestionsByIds(List<Integer> ids){
        return questionRepository.deleteByIds(ids);
    }

    public boolean updateQuestion(Integer id, Question question, int loggedInUserId){
        return questionRepository.updateQuestion(id, question, loggedInUserId);
    }

    public boolean updateQuestionChoicesByQuestionId(int quesId, List<QuestionChoice> questionChoices, int loggedInUserId) {
        return questionRepository.updateQuestionChoicesByQuestionId(quesId, questionChoices, loggedInUserId);
    }
}
