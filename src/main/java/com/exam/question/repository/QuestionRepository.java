package com.exam.question.repository;

import com.exam.common.repository.BaseRepository;
import com.exam.question.model.Question;
import com.exam.question.repository.rowmappers.QuestionRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepository extends BaseRepository {

    public static final StringBuilder FIND_ALL_QUESTION_QUERY = new StringBuilder("SELECT * FROM questions");
    public static final StringBuilder DELETE_ALL_QUESTION_QUERY = new StringBuilder("DELETE FROM questions");

    //CREATE QUERIES

    public int addQuestion(Question question, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("INSERT INTO questions(title, description, enabled, topic_id, created_by, modified_by)");
        sql.append(" VALUES (:title,:description,:enabled,:topicId, :createdBy, :modifiedBy");
        sql.append(") RETURNING id");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", question.getTitle());
        param.addValue("description", question.getDescription());
        param.addValue("enabled", question.isEnabled());
        param.addValue("topicId", question.getTopicId());
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
        final String sql = "SELECT * FROM questions WHERE id=:id";
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return (Question) npJdbcTemplate.queryForObject(sql, param, new QuestionRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Question findByTitle(String title){
        final String sql = "SELECT * FROM questions WHERE title=:title";
        final SqlParameterSource param = new MapSqlParameterSource("title",title);
        try{
            return (Question) npJdbcTemplate.queryForObject(sql, param, new QuestionRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Question> findAll(){
        try{
            return npJdbcTemplate.query(FIND_ALL_QUESTION_QUERY.toString(), new QuestionRowMapper());
        }catch (DataAccessException e){
            return null;
        }
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

    public boolean questionExistsByTitle(String title){
        final String sql = "SELECT EXISTS(SELECT 1 FROM questions where title=:title)";
        MapSqlParameterSource param = new MapSqlParameterSource("title", title);
        try {
            return npJdbcTemplate.queryForObject(sql, param, Boolean.class);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    //DELETE QUERIES

    public boolean delete(int id) {
        final String sql = "DELETE FROM questions WHERE id=:id";
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteByIds(List<Integer> ids){
        final String sql = "DELETE FROM questions WHERE id in (:ids)";
        final SqlParameterSource param = new MapSqlParameterSource().addValue("ids", ids);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteAll(){
        final String sql = DELETE_ALL_QUESTION_QUERY.toString();
        final SqlParameterSource param = new MapSqlParameterSource();
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    //UPDATE QUERIES

    public boolean updateQuestion(int id, Question question, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("UPDATE questions SET ");
        sql.append("title=:title,");
        sql.append("description=:description,");
        sql.append("enabled=:enabled,");
        sql.append("topic_id=:topicId ");
        sql.append("modified_by=:loggedInUserId ");
        sql.append("WHERE id=:id");

        MapSqlParameterSource param = new MapSqlParameterSource("id",id)
                .addValue("title", question.getTitle())
                .addValue("description", question.getDescription())
                .addValue("enabled", question.isEnabled())
                .addValue("topicId", question.getTopicId())
                .addValue("id", id)
                .addValue("loggedInUserId", loggedInUserId);
        try{
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }
}
