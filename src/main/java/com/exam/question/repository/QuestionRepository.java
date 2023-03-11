package com.exam.question.repository;

import com.exam.common.repository.BaseRepository;
import com.exam.question.model.Question;
import com.exam.question.model.QuestionChoice;
import com.exam.question.model.QuestionDataItem;
import com.exam.question.repository.rowmappers.QuestionDataItemRowMapper;
import com.exam.question.repository.rowmappers.QuestionRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionRepository extends BaseRepository {

    public static final StringBuilder FIND_ALL_QUESTION_QUERY = new StringBuilder("SELECT * FROM questions JOIN ");
    public static final StringBuilder DELETE_ALL_QUESTION_QUERY = new StringBuilder("DELETE FROM questions");

    //CREATE QUERIES

    @Transactional
    public int addQuestion(Question question, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("INSERT INTO questions(content, enabled, proficiency_id, topic_id, created_by, modified_by)");
        sql.append(" VALUES (:content, :enabled, :proficiencyId, :topicId, :createdBy, :modifiedBy");
        sql.append(") RETURNING id");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("content", question.getContent());
        param.addValue("enabled", question.isEnabled());
        param.addValue("proficiencyId", question.getProficiencyId());
        param.addValue("topicId", question.getTopicId());
        param.addValue("createdBy", loggedInUserId);
        param.addValue("modifiedBy", loggedInUserId);

        try{
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            npJdbcTemplate.update(sql.toString(), param, generatedKeyHolder);
            int questionId = generatedKeyHolder.getKey().intValue();

            if(questionId > 0 && question.getQuestionChoices() != null && question.getQuestionChoices().size() > 0){
                question.getQuestionChoices().forEach(questionChoice -> {
                    questionChoice.setQuestionId(questionId);
                    int choiceId = addQuestionChoice(questionChoice, loggedInUserId);
                    questionChoice.setId(choiceId);
                });
            }

            return questionId;

        }catch (EmptyResultDataAccessException e){
            return -1;
        }
    }

    private int addQuestionChoice(QuestionChoice questionChoice, int loggedInUserId) {
        final StringBuilder sql = new StringBuilder("INSERT INTO question_choices ");
        sql.append("(description, enabled, correct, ques_id, created_by, modified_by)");
        sql.append("VALUES(:description, :enabled, :correct, :ques_id, :createdBy, :modifiedBy)");
        sql.append(" RETURNING id");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("description", questionChoice.getDescription());
        param.addValue("enabled", questionChoice.isEnabled());
        param.addValue("correct", questionChoice.isCorrect());
        param.addValue("ques_id", questionChoice.getQuestionId());
        param.addValue("createdBy", loggedInUserId);
        param.addValue("modifiedBy", loggedInUserId);

        try{
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            npJdbcTemplate.update(sql.toString(), param, generatedKeyHolder);
            return generatedKeyHolder.getKey().intValue();
        }catch (EmptyResultDataAccessException e){
            return -1;
        }
    }


    //SELECT QUERIES

    public Question findById(int id){
        final StringBuilder sql = new StringBuilder("SELECT q.* , ");
        sql.append("qc.id as qc_id, ");
        sql.append("qc.description as qc_desc, ");
        sql.append("qc.enabled as qc_enabled, ");
        sql.append("qc.correct as qc_correct ");
        sql.append("FROM questions q ");
        sql.append("JOIN question_choices qc on qc.ques_id = q.id ");
        sql.append("WHERE q.id=:id ");
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            List<QuestionDataItem> dataItems = (List<QuestionDataItem>) npJdbcTemplate.query(sql.toString(), param, new QuestionDataItemRowMapper());
            List<Question> questions = new ArrayList<>(convertQuesDataItemsToQuestionList(dataItems).values());
            return questions.get(0);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Question findByTitle(String title){
        final StringBuilder sql = new StringBuilder("SELECT q.* , ");
        sql.append("qc.id as qc_id, ");
        sql.append("qc.content as qc_desc, ");
        sql.append("qc.enabled as qc_enabled, ");
        sql.append("qc.correct as qc_correct ");
        sql.append("FROM questions q ");
        sql.append("JOIN question_choices qc on qc.ques_id = q.id ");
        sql.append("WHERE title=:title");
        final SqlParameterSource param = new MapSqlParameterSource("title",title);
        try{
            List<QuestionDataItem> dataItems = (List<QuestionDataItem>) npJdbcTemplate.query(sql.toString(), param, new QuestionDataItemRowMapper());
            List<Question> questions = new ArrayList<>(convertQuesDataItemsToQuestionList(dataItems).values());
            return questions.get(0);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Question> findAll(){
        try{
            final StringBuilder sql = new StringBuilder("SELECT q.* , ");
            sql.append("qc.id as qc_id, ");
            sql.append("qc.description as qc_desc, ");
            sql.append("qc.enabled as qc_enabled, ");
            sql.append("qc.correct as qc_correct ");
            sql.append("FROM questions q ");
            sql.append("JOIN question_choices qc on qc.ques_id = q.id ");
            List<QuestionDataItem> dataItems = (List<QuestionDataItem>) npJdbcTemplate.query(sql.toString(), new QuestionDataItemRowMapper());
            return new ArrayList<>(convertQuesDataItemsToQuestionList(dataItems).values());
        }catch (DataAccessException e){
            return null;
        }
    }

    private Map<Integer, Question> convertQuesDataItemsToQuestionList(List<QuestionDataItem> dataItemList) {
        Map<Integer, Question> questionMap = new HashMap<>();
        for(QuestionDataItem item : dataItemList) {
            Integer quesId = item.getId();
            Question question = questionMap.get(quesId);
            if(question == null){
                question = new Question();
                question.setId(item.getId());
                question.setContent(item.getContent());
                question.setEnabled(item.isEnabled());
                question.setProficiencyId(item.getProficiencyId());
                question.setTopicId(item.getTopicId());

                List<QuestionChoice> choices = new ArrayList<>();
                if(item.getQuestionChoice() != null){
                    item.getQuestionChoice().setQuestionId(question.getId());
                }
                choices.add(item.getQuestionChoice());
                question.setQuestionChoices(choices);
            }else {
                List<QuestionChoice> choices = question.getQuestionChoices();
                if(choices == null) {
                    choices = new ArrayList<>();
                }
                if(item.getQuestionChoice() != null){
                    item.getQuestionChoice().setQuestionId(question.getId());
                }
                choices.add(item.getQuestionChoice());
                question.setQuestionChoices(choices);
            }

            questionMap.put(question.getId(), question);
        }
        return questionMap;
    }

    public int findTotalCount(){
        final String sql = "SELECT count(*) from questions;";
        try{
            Integer count = npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(),Integer.class);
            return count != null ? count : 0 ;
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    public List<Question> findByTopicId(Integer topicId) {
        final String sql = "SELECT * FROM questions WHERE topic_id=:topicId";
        final SqlParameterSource param = new MapSqlParameterSource("topicId",topicId);
        try{
            return npJdbcTemplate.query(sql, param, new QuestionRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public boolean questionExistsByContent(String content){
        final String sql = "SELECT EXISTS(SELECT 1 FROM questions where content=:content)";
        MapSqlParameterSource param = new MapSqlParameterSource("content", content);
        try {
            return npJdbcTemplate.queryForObject(sql, param, Boolean.class);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    //DELETE QUERIES

    @Transactional
    public boolean delete(int id) {
        boolean choiceDeleteStatus = deleteQuestionChoiceByQuestionId(id);
        if(choiceDeleteStatus){
            final String sql = "DELETE FROM questions WHERE id=:id";
            final SqlParameterSource param = new MapSqlParameterSource("id",id);
            try{
                return npJdbcTemplate.update(sql, param) > 0;
            }catch (DataAccessException e){
                return false;
            }
        }
        return false;
    }

    private boolean deleteQuestionChoiceByQuestionId(int quesId) {
        final String sql = "DELETE FROM question_choices qc WHERE qc.ques_id=:quesId";
        final SqlParameterSource param = new MapSqlParameterSource("quesId", quesId);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch(DataAccessException e){
            return false;
        }
    }

    @Transactional
    public boolean deleteByIds(List<Integer> ids){
        boolean choicesDeleteStatus = deleteQuestionChoicesByQuestionIds(ids);
        if(choicesDeleteStatus) {
            final String sql = "DELETE FROM questions WHERE id in (:ids)";
            final SqlParameterSource param = new MapSqlParameterSource().addValue("ids", ids);
            try {
                return npJdbcTemplate.update(sql, param) > 0;
            } catch (DataAccessException e) {
                return false;
            }
        }
        return false;
    }

    private boolean deleteQuestionChoicesByQuestionIds(List<Integer> quesIds) {
        final String sql = "DELETE FROM question_choices qc WHERE qc.ques_id in (:quesIds)";
        final SqlParameterSource param = new MapSqlParameterSource("quesIds", quesIds);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch(DataAccessException e){
            return false;
        }
    }

    @Transactional
    public boolean deleteAll(){
        boolean choicesDeleteStatus = deleteAllQuestionChoicesForAllQuestions();
        if(choicesDeleteStatus) {
            final String sql = DELETE_ALL_QUESTION_QUERY.toString();
            final SqlParameterSource param = new MapSqlParameterSource();
            try {
                return npJdbcTemplate.update(sql, param) > 0;
            } catch (DataAccessException e) {
                return false;
            }
        }
        return false;
    }

    private boolean deleteAllQuestionChoicesForAllQuestions(){
        final String sql = "DELETE FROM question_choices";
        final SqlParameterSource param = new MapSqlParameterSource();
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    //UPDATE QUERIES

    @Transactional
    public boolean updateQuestion(int id, Question question, int loggedInUserId){
        Question quesOldData = findById(id);
        List<QuestionChoice> oldChoices = quesOldData.getQuestionChoices();
        List<QuestionChoice> newChoices = question.getQuestionChoices();

        boolean choicesModified = true;
        if(oldChoices != null){
            choicesModified = !oldChoices.equals(newChoices);
        }

        boolean choicesUpdateStatus = true;
        if(choicesModified && newChoices != null){
            boolean oldChoicesDeleted = deleteQuestionChoiceByQuestionId(id);
            if(oldChoicesDeleted){
                for (QuestionChoice newChoice : newChoices) {
                    newChoice.setQuestionId(id);
                    int choiceId = addQuestionChoice(newChoice, loggedInUserId);
                    newChoice.setId(choiceId);
                    choicesUpdateStatus = choiceId > 0;
                }
            }
        }

        if(choicesUpdateStatus) {
            final StringBuilder sql = new StringBuilder("UPDATE questions SET ");
            sql.append("content=:content,");
            sql.append("enabled=:enabled,");
            sql.append("proficiency_id=:proficiencyId, ");
            sql.append("topic_id=:topicId, ");
            sql.append("modified_by=:loggedInUserId ");
            sql.append("WHERE id=:id");

            MapSqlParameterSource param = new MapSqlParameterSource("id", id)
                    .addValue("content", question.getContent())
                    .addValue("enabled", question.isEnabled())
                    .addValue("proficiencyId", question.getProficiencyId())
                    .addValue("topicId", question.getTopicId())
                    .addValue("id", id)
                    .addValue("loggedInUserId", loggedInUserId);
            try {
                return npJdbcTemplate.update(sql.toString(), param) > 0;
            } catch (DataAccessException e) {
                return false;
            }
        }
        return false;
    }

    private boolean updateQuestionChoices(int choiceId, QuestionChoice choice, int loggedInUserId) {
        final StringBuilder sql = new StringBuilder("UPDATE question_choices SET ");
        sql.append("description=:description,");
        sql.append("enabled=:enabled,");
        sql.append("correct=:correct,");
        sql.append("modified_by=:loggedInUserId ");
        sql.append("WHERE id=:choiceId");

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("description", choice.getDescription())
                .addValue("enabled", choice.isEnabled())
                .addValue("correct", choice.isCorrect())
                .addValue("choiceId", choiceId)
                .addValue("loggedInUserId", loggedInUserId);
        try {
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Transactional
    public boolean updateQuestionChoicesByQuestionId(int quesId, List<QuestionChoice> questionChoices, int loggedInUserId) {
        boolean choicesUpdateStatus = true;
        boolean oldChoicesDeleted = deleteQuestionChoiceByQuestionId(quesId);
        if(oldChoicesDeleted){
            for (QuestionChoice newChoice : questionChoices) {
                newChoice.setQuestionId(quesId);
                int choiceId = addQuestionChoice(newChoice, loggedInUserId);
                newChoice.setId(choiceId);
                choicesUpdateStatus = choiceId > 0;
            }
        }
        return choicesUpdateStatus;
    }
}
