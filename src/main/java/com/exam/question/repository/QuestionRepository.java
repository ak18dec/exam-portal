package com.exam.question.repository;

import com.exam.common.repository.BaseRepository;
import com.exam.question.model.Question;
import com.exam.question.repository.rowmappers.QuestionRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepository extends BaseRepository {

    public static final StringBuilder FIND_SINGLE_QUESTION_QUERY = new StringBuilder("SELECT * FROM question WHERE ");
    public static final StringBuilder FIND_ALL_QUESTION_QUERY = new StringBuilder("SELECT * FROM question");

    public static final StringBuilder DELETE_SINGLE_QUESTION_QUERY = new StringBuilder("DELETE FROM question WHERE ");
    public static final StringBuilder DELETE_ALL_QUESTION_QUERY = new StringBuilder("DELETE FROM question");
    public static final StringBuilder DELETE_LIST_OF_QUESTION_QUERY = new StringBuilder("DELETE FROM question WHERE ");

    //CREATE QUERIES

    public int addQuestion(Question question){
        final StringBuilder sql = new StringBuilder("INSERT INTO question(title, content, enabled, topic_id");
        sql.append(" VALUES (:title,:content,:enabled,:topicId");
        sql.append(")");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", question.getTitle());
        param.addValue("content", question.getContent());
        param.addValue("enabled", question.isEnabled());
        param.addValue("topicId", question.getTopicId());

        try{
            return npJdbcTemplate.update(sql.toString(), param);
        }catch (EmptyResultDataAccessException e){
            return -1;
        }
    }


    //SELECT QUERIES

    public Question findById(int id){
        final String sql = FIND_SINGLE_QUESTION_QUERY.append("id=:id").toString();
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return (Question) npJdbcTemplate.queryForObject(sql, param, new QuestionRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Question findByTitle(String title){
        final String sql = FIND_SINGLE_QUESTION_QUERY.append("title=:title").toString();
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
        final String sql = "SELECT count(*) from question;";
        try{
            Integer count = npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(),Integer.class);
            return count != null ? count : 0 ;
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    public List<Question> findByTopicId(Integer topicId) {
        final String sql = FIND_SINGLE_QUESTION_QUERY.append("topic_id=:topicId").toString();
        final SqlParameterSource param = new MapSqlParameterSource("topicId",topicId);
        try{
            return npJdbcTemplate.query(sql, param, new QuestionRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public boolean questionExistsByTitle(String title){
        final String sql = "SELECT EXISTS(SELECT 1 FROM question where title=:title)";
        MapSqlParameterSource param = new MapSqlParameterSource("title", title);
        try {
            return npJdbcTemplate.queryForObject(sql, param, Boolean.class);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    //DELETE QUERIES

    public boolean delete(int id) {
        final String sql = DELETE_SINGLE_QUESTION_QUERY.append("id=:id").toString();
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteByIds(List<Integer> ids){
        final String sql = DELETE_LIST_OF_QUESTION_QUERY.append("id in (:ids)").toString();
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

    public boolean updateQuestion(int id, Question question){
        final StringBuilder sql = new StringBuilder("UPDATE question SET ");
        sql.append("title=:title,");
        sql.append("content=:content,");
        sql.append("enabled=:enabled,");
        sql.append("topic_id=:topicId ");
        sql.append("WHERE id=:id");

        MapSqlParameterSource param = new MapSqlParameterSource("id",id)
                .addValue("title", question.getTitle())
                .addValue("content", question.getContent())
                .addValue("enabled", question.isEnabled())
                .addValue("topicId", question.getTopicId())
                .addValue("id", id);
        try{
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }
}
