package com.exam.topic.repository;

import com.exam.common.repository.BaseRepository;
import com.exam.topic.model.Topic;
import com.exam.topic.repository.rowmappers.TopicRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicRepository extends BaseRepository {

    public static final StringBuilder FIND_SINGLE_TOPIC_QUERY = new StringBuilder("SELECT * FROM topic WHERE ");
    public static final StringBuilder FIND_ALL_TOPIC_QUERY = new StringBuilder("SELECT * FROM topic");

    public static final StringBuilder DELETE_SINGLE_TOPIC_QUERY = new StringBuilder("DELETE FROM topic WHERE ");
    public static final StringBuilder DELETE_ALL_TOPIC_QUERY = new StringBuilder("DELETE FROM topic");
    public static final StringBuilder DELETE_LIST_OF_TOPIC_QUERY = new StringBuilder("DELETE FROM topic WHERE ");

    //CREATE QUERIES

    public int addTopic(Topic topic){
        final StringBuilder sql = new StringBuilder("INSERT INTO topic(title, description, enabled, category_id");
        sql.append(" VALUES (:title,:description,:enabled,:categoryId");
        sql.append(")");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", topic.getTitle());
        param.addValue("description", topic.getDescription());
        param.addValue("enabled", topic.isEnabled());
        param.addValue("categoryId", topic.getCategoryId());

        try{
            return npJdbcTemplate.update(sql.toString(), param);
        }catch (EmptyResultDataAccessException e){
            return -1;
        }
    }


    //SELECT QUERIES

    public Topic findById(int id){
        final String sql = FIND_SINGLE_TOPIC_QUERY.append("id=:id").toString();
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return (Topic) npJdbcTemplate.queryForObject(sql, param, new TopicRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Topic findByTitle(String title){
        final String sql = FIND_SINGLE_TOPIC_QUERY.append("title=:title").toString();
        final SqlParameterSource param = new MapSqlParameterSource("title",title);
        try{
            return (Topic) npJdbcTemplate.queryForObject(sql, param, new TopicRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Topic> findAll(){
        try{
            return npJdbcTemplate.query(FIND_ALL_TOPIC_QUERY.toString(), new TopicRowMapper());
        }catch (DataAccessException e){
            return null;
        }
    }

    public int findTotalCount(){
        final String sql = "SELECT count(*) from topic;";
        try{
            Integer count = npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(),Integer.class);
            return count != null ? count : 0 ;
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    public List<Topic> findByCategoryId(Integer categoryId) {
        final String sql = FIND_SINGLE_TOPIC_QUERY.append("category_id=:categoryId").toString();
        final SqlParameterSource param = new MapSqlParameterSource("categoryId",categoryId);
        try{
            return npJdbcTemplate.query(sql, param, new TopicRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public boolean topicExistsByTitle(String title){
        final String sql = "SELECT EXISTS(SELECT 1 FROM topic where title=:title)";
        MapSqlParameterSource param = new MapSqlParameterSource("title", title);
        try {
            return npJdbcTemplate.queryForObject(sql, param, Boolean.class);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    //DELETE QUERIES

    public boolean delete(int id) {
        final String sql = DELETE_SINGLE_TOPIC_QUERY.append("id=:id").toString();
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteByIds(List<Integer> ids){
        final String sql = DELETE_LIST_OF_TOPIC_QUERY.append("id in (:ids)").toString();
        final SqlParameterSource param = new MapSqlParameterSource().addValue("ids", ids);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteAll(){
        final String sql = DELETE_ALL_TOPIC_QUERY.toString();
        final SqlParameterSource param = new MapSqlParameterSource();
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    //UPDATE QUERIES

    public boolean updateTopic(int id, Topic topic){
        final StringBuilder sql = new StringBuilder("UPDATE topic SET ");
        sql.append("title=:title,");
        sql.append("description=:description,");
        sql.append("enabled=:enabled,");
        sql.append("category_id=:categoryId ");
        sql.append("WHERE id=:id");

        MapSqlParameterSource param = new MapSqlParameterSource("id",id)
                .addValue("title", topic.getTitle())
                .addValue("description", topic.getDescription())
                .addValue("enabled", topic.isEnabled())
                .addValue("categoryId", topic.getCategoryId())
                .addValue("id", id);
        try{
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }
}
