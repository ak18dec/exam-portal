package com.exam.quiz.repository.resultsetextractors;

import com.exam.question.model.Question;
import com.exam.quiz.model.Quiz;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizResultSetExtractor implements ResultSetExtractor<List<Quiz>> {
    @Override
    public List<Quiz> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Quiz> quizMap = new HashMap<>();

        while(rs.next()){
            int quizId = rs.getInt("id");
            Quiz quiz = quizMap.get(quizId);
            if(quiz == null){
                quiz = new Quiz();
                quiz.setId(quizId);
                quiz.setTitle(rs.getString("title"));
                quiz.setDescription(rs.getString("description"));
                quiz.setPublished(rs.getBoolean("published"));
                quiz.setMaxMarks(rs.getInt("max_marks"));
                quiz.setMaxTime(rs.getInt("max_time"));
                quizMap.put(quizId, quiz);
            }

            List<Integer> questionIds = quiz.getQuestionIds();
            if(questionIds == null){
                questionIds = new ArrayList<>();
                quiz.setQuestionIds(questionIds);
            }
            int quesId = rs.getInt("mapping_id");
            if(quesId > 0) {
                questionIds.add(quesId);
            }
        }
        return new ArrayList<>(quizMap.values());
    }
}
