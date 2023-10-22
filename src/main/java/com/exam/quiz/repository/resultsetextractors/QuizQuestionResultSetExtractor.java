package com.exam.quiz.repository.resultsetextractors;

import com.exam.question.model.Question;
import com.exam.question.model.QuestionChoice;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class QuizQuestionResultSetExtractor implements ResultSetExtractor<List<Question>> {

    @Override
    public List<Question> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Question> questionMap = new HashMap<>();
        while(rs.next()){
            int quesId = rs.getInt("quesId");
            Question question = questionMap.get(quesId);
            if(question == null){
                question = new Question();
                question.setId(quesId);
                question.setDescription(rs.getString("quesContent"));
                questionMap.put(quesId, question);
            }
            List<QuestionChoice> choices = question.getQuestionChoices();
            if(choices == null){
                choices = new ArrayList<>();
                question.setQuestionChoices(choices);
            }
            QuestionChoice choice = new QuestionChoice();
            choice.setId(rs.getInt("choiceId"));
            choice.setDescription(rs.getString("choice"));
            choice.setCorrect(rs.getBoolean("correctChoice"));
            choices.add(choice);
        }
        return new ArrayList<Question>(questionMap.values());
    }
}
