package com.exam.quiz.repository;

import com.exam.common.repository.BaseRepository;
import com.exam.question.model.Question;
import com.exam.quiz.model.Quiz;
import com.exam.quiz.repository.resultsetextractors.QuizQuestionResultSetExtractor;
import com.exam.quiz.repository.resultsetextractors.QuizResultSetExtractor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuizRepository extends BaseRepository {

    //CREATE QUERIES

    @Transactional
    public int addQuiz(Quiz quiz, int loggedInUserId) {
        final StringBuilder sql = new StringBuilder("INSERT INTO quiz(title, description, published, proficiency_id, max_marks, max_time, instruction_enabled, created_by, modified_by)");
        sql.append(" VALUES (:title, :description, :published, :proficiencyId, :maxMarks, :maxTime, :instructionEnabled, :createdBy, :modifiedBy");
        sql.append(") RETURNING id");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", quiz.getTitle());
        param.addValue("description", quiz.getDescription());
        param.addValue("published", quiz.isPublished());
        param.addValue("proficiencyId", quiz.getProficiencyId());
        param.addValue("maxMarks", quiz.getMaxMarks());
        param.addValue("maxTime", quiz.getMaxTime());
        param.addValue("instructionEnabled", quiz.isInstructionEnabled());
        param.addValue("createdBy", loggedInUserId);
        param.addValue("modifiedBy", loggedInUserId);

        try{
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            npJdbcTemplate.update(sql.toString(), param, generatedKeyHolder);
            int quizId = generatedKeyHolder.getKey().intValue();

            if(quizId > 0){
                if(quiz.getQuestionIds() != null && quiz.getQuestionIds().size() > 0){
                    int[] qqIds = addQuizQuestions(quiz.getQuestionIds(), loggedInUserId, quizId);
                }

                if(quiz.isInstructionEnabled() && quiz.getInstructionIds() != null && quiz.getInstructionIds().size() > 0){
                    int[] qiIds = addQuizInstructions(quiz.getInstructionIds(), loggedInUserId, quizId);
                }
            }

            return quizId;

        }catch (EmptyResultDataAccessException e){
            return -1;
        }
    }

    public int[] addQuizQuestions(List<Integer> questionIds, int loggedInUserId, int quizId){
        System.out.println("Adding New Questions to Quiz ID: "+quizId);
        final String sql = "INSERT INTO quiz_ques(quiz_id, question_id) VALUES (:quizId, :questionId)";
        List<MapSqlParameterSource> params = new ArrayList<>();
        for (Integer quesId : questionIds) {
            MapSqlParameterSource source = new MapSqlParameterSource();
            source.addValue("quizId", quizId);
            source.addValue("questionId", quesId);
            params.add(source);
        }
        return npJdbcTemplate.batchUpdate(sql, params.toArray(new MapSqlParameterSource[0]));
    }

    public int[] addQuizInstructions(List<Integer> instructionIds, int loggedInUserId, int quizId){
        final String sql = "INSERT INTO quiz_instruction(quiz_id, instruction_id) VALUES (:quizId, :instructionId)";
        List<MapSqlParameterSource> params = new ArrayList<>();
        for(Integer instr : instructionIds){
            MapSqlParameterSource source = new MapSqlParameterSource();
            source.addValue("quizId", quizId);
            source.addValue("instructionId", instr);
            params.add(source);
        }
        return npJdbcTemplate.batchUpdate(sql, params.toArray(new MapSqlParameterSource[0]));
    }

    //UPDATE QUERIES

    @Transactional
    public boolean updateQuiz(int id, Quiz quiz, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("UPDATE quiz SET ");
        sql.append("title=:title, ");
        sql.append("description=:description, ");
        sql.append("published=:published, ");
        sql.append("proficiency_id=:proficiencyId, ");
        sql.append("max_marks=:maxMarks, ");
        sql.append("max_time=:maxTime, ");
        sql.append("modified_by=:loggedInUserId ");
        sql.append("WHERE id=:id ");

        MapSqlParameterSource param = new MapSqlParameterSource("id", id)
                .addValue("title", quiz.getTitle())
                .addValue("description", quiz.getDescription())
                .addValue("published", quiz.isPublished())
                .addValue("proficiencyId", quiz.getProficiencyId())
                .addValue("maxMarks", quiz.getMaxMarks())
                .addValue("maxTime", quiz.getMaxTime())
                .addValue("loggedInUserId", loggedInUserId);
        try {
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Transactional
    public boolean updateQuizQuestions(int id, List<Integer> questionIds, int loggedInUserId){
        System.out.println("----------Updating Quiz Questions-----------");
        boolean deleteOldQuestions = deleteQuestions(id);
        System.out.println("deleteOldQuestions: "+deleteOldQuestions);
        if(deleteOldQuestions){
            int[] updatedQuesIds = addQuizQuestions(questionIds, loggedInUserId, id);
            System.out.println("New Added Questions: "+updatedQuesIds.toString());
            return updatedQuesIds.length > 0;
        }
        return false;
    }

    @Transactional
    public boolean updateQuizStatus(int id, boolean status, int loggedInUserId) {
        final String sql = "UPDATE quiz SET published=:status,modified_by=:loggedInUserId WHERE id=:id";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("status", status);
        param.addValue("id", id);
        param.addValue("loggedInUserId", loggedInUserId);
        try {
            return npJdbcTemplate.update(sql, param) > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Transactional
    public boolean updateInstructions(int id, List<Integer> instructionsIds, int loggedInUserId){
        boolean deleteOldInstructions = deleteInstructions(id);
        if(deleteOldInstructions){
            int[] updatedInstructionIds = addQuizInstructions(instructionsIds, loggedInUserId, id);
            return updatedInstructionIds.length > 0;
        }
        return false;
    }

    @Transactional
    public boolean toggleInstructions(int id, boolean instructionEnable, List<Integer> instructionIds){
        final String sql = "UPDATE quiz SET instruction_enabled=:instructionEnable WHERE id=:id";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("instructionEnable", instructionEnable);
        param.addValue("id", id);
        try {
            boolean instructionToggled = npJdbcTemplate.update(sql, param) > 0;
            boolean instructionsDeleted = false;
            if(!instructionEnable && instructionToggled){
                instructionsDeleted = deleteInstructions(id);
            }
            return instructionToggled && instructionsDeleted;
        } catch (DataAccessException e) {
            return false;
        }
    }

    private boolean deleteInstructions(int quizId){
        final String sql = "DELETE FROM quiz_instruction WHERE quiz_id=:quizId";
        final MapSqlParameterSource param = new MapSqlParameterSource("quizId", quizId);
        try {
            return npJdbcTemplate.update(sql, param) > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    private boolean deleteQuestions(int quizId){
        final String sql = "DELETE FROM quiz_ques WHERE quiz_id=:quizId";
        final MapSqlParameterSource param = new MapSqlParameterSource("quizId", quizId);
        try {
            return npJdbcTemplate.update(sql, param) > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    //DELETE QUERIES

    public boolean delete(int id){
        boolean questionsDeleted = deleteQuestions(id);
        boolean instructionsDeleted = deleteInstructions(id);
        final String sql = "DELETE FROM quiz WHERE id=:id";
        final MapSqlParameterSource param = new MapSqlParameterSource("id", id);
        try{
            boolean quizDeleted = npJdbcTemplate.update(sql, param) > 0;
            return quizDeleted && questionsDeleted && instructionsDeleted;
        }catch (DataAccessException e){
            return false;
        }
    }

    //SELECT QUERIES

    public List<Quiz> findAll() {
        try{
            final StringBuilder sql = new StringBuilder();
            sql.append("select q.* from ( ");
            sql.append("SELECT quiz.* ,'question' QUES_OR_INSTRUCTION , qq.question_id as mapping_id ");
            sql.append("FROM quiz as quiz LEFT JOIN quiz_ques qq on qq.quiz_id = quiz.id ");
            sql.append("UNION ");
            sql.append("SELECT quiz.* , 'instruction' QUES_OR_INSTRUCTION, qi.instruction_id as mapping_id ");
            sql.append("from quiz as quiz LEFT JOIN quiz_instruction qi on qi.quiz_id = quiz.id ");
            sql.append(" ) as q ");
            sql.append("order by q.id, q.QUES_OR_INSTRUCTION ");

            return npJdbcTemplate.query(sql.toString(), new QuizResultSetExtractor());

        }catch (DataAccessException e){
            return null;
        }
    }

    public Quiz findQuizById(int id) {
        try{
            final StringBuilder sql = new StringBuilder();
            sql.append("select q.* from ( ");
            sql.append("SELECT quiz.* ,'question' QUES_OR_INSTRUCTION , qq.question_id as mapping_id ");
            sql.append("FROM quiz as quiz LEFT JOIN quiz_ques qq on qq.quiz_id = quiz.id WHERE quiz.id=:id ");
            sql.append("UNION ");
            sql.append("SELECT quiz.* , 'instruction' QUES_OR_INSTRUCTION, qi.instruction_id as mapping_id ");
            sql.append("from quiz as quiz LEFT JOIN quiz_instruction qi on qi.quiz_id = quiz.id WHERE quiz.id=:id ");
            sql.append(" ) as q ");
            sql.append("order by q.id, q.QUES_OR_INSTRUCTION ");

            final SqlParameterSource param = new MapSqlParameterSource("id",id);

            List<Quiz> quizzes = npJdbcTemplate.query(sql.toString(), param, new QuizResultSetExtractor());
            return quizzes != null && !quizzes.isEmpty() ? quizzes.get(0) : null;

        }catch (DataAccessException e){
            return null;
        }
    }

    public List<Question> findQuestionsByQuizId(int id) {
        try {
            final StringBuilder sql = new StringBuilder();
            sql.append("SELECT q.id as quesId, q.content as quesContent, ");
            sql.append("qc.id as choiceId, qc.description as choice,qc.correct as correctChoice ");
            sql.append("FROM questions q ");
            sql.append("JOIN quiz_ques qq on qq.question_id = q.id ");
            sql.append("JOIN question_choices qc on qc.ques_id = q.id ");
            sql.append("WHERE qq.quiz_id = :id ");
            sql.append("ORDER BY quesId ");

            final SqlParameterSource param = new MapSqlParameterSource("id",id);

            return npJdbcTemplate.query(sql.toString(), param, new QuizQuestionResultSetExtractor());
        }catch (DataAccessException e){
            return null;
        }
    }
}
