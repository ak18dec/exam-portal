package com.exam.topic.repository;

import com.exam.common.repository.BaseRepository;
import com.exam.topic.model.Topic;
import com.exam.topic.repository.rowmappers.TopicRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicRepository extends BaseRepository {

    public static final StringBuilder FIND_ALL_TOPIC_QUERY = new StringBuilder("SELECT * FROM topics");
    public static final StringBuilder DELETE_ALL_TOPIC_QUERY = new StringBuilder("DELETE FROM topics");

    //CREATE QUERIES

    public int addTopic(Topic topic, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("INSERT INTO topics(title, description, enabled, category_id, created_by, modified_by)");
        sql.append(" VALUES (:title,:description,:enabled,:categoryId, :createdBy, :modifiedBy");
        sql.append(") RETURNING id");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", topic.getTitle());
        param.addValue("description", topic.getDescription());
        param.addValue("enabled", topic.isEnabled());
        param.addValue("categoryId", topic.getCategoryId());
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

    public Topic findById(int id){
        final String sql = "SELECT * FROM topics WHERE id=:id";
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return (Topic) npJdbcTemplate.queryForObject(sql, param, new TopicRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Topic findByTitle(String title){
        final String sql = "SELECT * FROM topics WHERE title=:title";
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
        final String sql = "SELECT count(*) from topics;";
        try{
            Integer count = npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(),Integer.class);
            return count != null ? count : 0 ;
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    public List<Topic> findByCategoryId(Integer categoryId) {
        final String sql = "SELECT * FROM topics WHERE category_id=:categoryId";
        final SqlParameterSource param = new MapSqlParameterSource("categoryId",categoryId);
        try{
            return npJdbcTemplate.query(sql, param, new TopicRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public boolean topicExistsByTitle(String title){
        final String sql = "SELECT EXISTS(SELECT 1 FROM topics where title=:title)";
        MapSqlParameterSource param = new MapSqlParameterSource("title", title);
        try {
            return npJdbcTemplate.queryForObject(sql, param, Boolean.class);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    //DELETE QUERIES

    public boolean delete(int id) {
        final String sql = "DELETE FROM topics WHERE id=:id";
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteByIds(List<Integer> ids){
        final String sql = "DELETE FROM topics WHERE id in (:ids)";
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

    public boolean updateTopic(int id, Topic topic, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("UPDATE topics SET ");
        sql.append("title=:title,");
        sql.append("description=:description,");
        sql.append("enabled=:enabled,");
        sql.append("category_id=:categoryId, ");
        sql.append("modified_by=:loggedInUserId ");
        sql.append("WHERE id=:id");

        MapSqlParameterSource param = new MapSqlParameterSource("id",id)
                .addValue("title", topic.getTitle())
                .addValue("description", topic.getDescription())
                .addValue("enabled", topic.isEnabled())
                .addValue("categoryId", topic.getCategoryId())
                .addValue("id", id)
                .addValue("loggedInUserId", loggedInUserId);
        try{
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }
}
