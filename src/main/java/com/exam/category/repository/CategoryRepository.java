package com.exam.category.repository;

import com.exam.category.model.Category;
import com.exam.category.repository.rowmappers.CategoryRowMapper;
import com.exam.common.repository.BaseRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository extends BaseRepository {

    public static final StringBuilder FIND_ALL_CATEGORY_QUERY = new StringBuilder("SELECT * FROM categories");
    public static final StringBuilder DELETE_ALL_CATEGORY_QUERY = new StringBuilder("DELETE FROM categories");

    //CREATE QUERIES

    public int addCategory(Category category, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("INSERT INTO categories (title, description, enabled, subject_id, created_by, modified_by)");
        sql.append(" VALUES (:title,:description,:enabled,:subjectId, :createdBy, :modifiedBy");
        sql.append(") RETURNING id");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", category.getTitle());
        param.addValue("description", category.getDescription());
        param.addValue("enabled", category.isEnabled());
        param.addValue("subjectId", category.getSubjectId());
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

    public Category findById(int id){
        final String sql = "SELECT * FROM categories WHERE id=:id";
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return (Category) npJdbcTemplate.queryForObject(sql, param, new CategoryRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Category findByTitle(String title){
        final String sql = "SELECT * FROM categories WHERE title=:title";
        final SqlParameterSource param = new MapSqlParameterSource("title",title);
        try{
            return (Category) npJdbcTemplate.queryForObject(sql, param, new CategoryRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Category> findAll(){
        try{
            return npJdbcTemplate.query(FIND_ALL_CATEGORY_QUERY.toString(), new CategoryRowMapper());
        }catch (DataAccessException e){
            return null;
        }
    }

    public int findTotalCount(){
        final String sql = "SELECT count(*) from categories;";
        try{
            Integer count = npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(),Integer.class);
            return count != null ? count : 0 ;
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    public List<Category> findBySubjectId(Integer subjectId) {
        final String sql = "SELECT * FROM categories WHERE subject_id=:subjectId";
        final SqlParameterSource param = new MapSqlParameterSource("subjectId",subjectId);
        try{
            return npJdbcTemplate.query(sql, param, new CategoryRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public boolean categoryExistsByTitle(String title){
        final String sql = "SELECT EXISTS(SELECT 1 FROM categories where title=:title)";
        MapSqlParameterSource param = new MapSqlParameterSource("title", title);
        try {
            return npJdbcTemplate.queryForObject(sql, param, Boolean.class);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    //DELETE QUERIES

    public boolean delete(int id) {
        final String sql = "DELETE FROM categories WHERE id=:id";
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteByIds(List<Integer> ids){
        final String sql = "DELETE FROM categories WHERE id in (:ids)";
        final SqlParameterSource param = new MapSqlParameterSource().addValue("ids", ids);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteAll(){
        final String sql = DELETE_ALL_CATEGORY_QUERY.toString();
        final SqlParameterSource param = new MapSqlParameterSource();
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    //UPDATE QUERIES

    public boolean updateCategory(int id, Category category, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("UPDATE category SET ");
        sql.append("title=:title,");
        sql.append("description=:description,");
        sql.append("enabled=:enabled,");
        sql.append("subject_id=:subjectId ");
        sql.append("modified_by=:loggedInUserId ");
        sql.append("WHERE id=:id");

        MapSqlParameterSource param = new MapSqlParameterSource("id",id)
                .addValue("title", category.getTitle())
                .addValue("description", category.getDescription())
                .addValue("enabled", category.isEnabled())
                .addValue("subjectId", category.getSubjectId())
                .addValue("id", id)
                .addValue("loggedInUserId", loggedInUserId);
        try{
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }
}
