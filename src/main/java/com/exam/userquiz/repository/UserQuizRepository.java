package com.exam.userquiz.repository;

import com.exam.common.repository.BaseRepository;
import com.exam.userquiz.model.AttemptedQuiz;
import com.exam.userquiz.model.AttemptedQuizQuestion;
import com.exam.userquiz.model.UserAttemptedQuiz;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserQuizRepository extends BaseRepository {

    @Transactional
    @Async
    public int submitQuiz(UserAttemptedQuiz userAttemptedQuiz) {
        int quizId = addAttemptedQuiz(userAttemptedQuiz.getQuiz());

        if(quizId <= 0) {
            return -1;
        }

        final StringBuilder sql = new StringBuilder("INSERT INTO user_attempted_quiz ");
        sql.append("(attempted_quiz_id, user_id, user_full_name, username, attempted_on, proficiency, max_marks, max_time, user_time, score)");
        sql.append(" VALUES (:quizId, :description, :published, :proficiencyId, :maxMarks, :maxTime, :instructionEnabled, :createdBy, :modifiedBy");
        sql.append(") RETURNING id");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("quizId", quizId);
        param.addValue("userId", userAttemptedQuiz.getUserId());
        param.addValue("userFullName", userAttemptedQuiz.getUserFullName());
        param.addValue("username", userAttemptedQuiz.getUsername());
        param.addValue("attemptedOn", userAttemptedQuiz.getAttemptedOn());
        param.addValue("proficiency", userAttemptedQuiz.getProficiencyId());
        param.addValue("maxMarks", userAttemptedQuiz.getMaxMarks());
        param.addValue("maxTime", userAttemptedQuiz.getMaxTime());
        param.addValue("userTime", userAttemptedQuiz.getUserTime());
        param.addValue("score", userAttemptedQuiz.getScore());

        try {
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            npJdbcTemplate.update(sql.toString(), param, generatedKeyHolder);
            return generatedKeyHolder.getKey().intValue();
        } catch (EmptyResultDataAccessException e) {
            return -1;
        }

    }

    private int addAttemptedQuiz(AttemptedQuiz attemptedQuiz){
        final StringBuilder sql = new StringBuilder("INSERT INTO attempted_quiz(title, description)");
        sql.append(" VALUES (:title, :description) RETURNING id");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", attemptedQuiz.getTitle());
        param.addValue("description", attemptedQuiz.getDescription());

        try{
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            npJdbcTemplate.update(sql.toString(), param, generatedKeyHolder);
            int quizId = generatedKeyHolder.getKey().intValue();

            if(quizId > 0){
                if(attemptedQuiz.getQuestions() != null && attemptedQuiz.getQuestions().size() > 0){
                    addAttemptedQuizQuestions(attemptedQuiz.getQuestions(), quizId);
                }
            }
            return quizId;
        }catch (EmptyResultDataAccessException e){
            return -1;
        }
    }

    private int[] addAttemptedQuizQuestions(List<AttemptedQuizQuestion> attemptedQuizQuestions, int quizId){
        System.out.println("Adding Attempted Questions to Quiz ID: "+quizId);
        final String sql = "INSERT INTO attempted_quiz_questions (attempted_quiz_id, question, option_selected) VALUES (:quizId, :question, :optionSelected)";
        List<MapSqlParameterSource> params = new ArrayList<>();
        for (AttemptedQuizQuestion attemptedQuizQuestion : attemptedQuizQuestions) {
            MapSqlParameterSource source = new MapSqlParameterSource();
            source.addValue("quizId", quizId);
            source.addValue("question", attemptedQuizQuestion.getQuestion());
            source.addValue("optionSelected", attemptedQuizQuestion.getOptionSelected());
            params.add(source);
        }
        return npJdbcTemplate.batchUpdate(sql, params.toArray(new MapSqlParameterSource[0]));
    }
}
