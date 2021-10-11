package com.exam.subject.repository;

import com.exam.common.repository.BaseRepository;
import com.exam.subject.model.Subject;
import com.exam.subject.repository.rowmappers.SubjectRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectRepository extends BaseRepository {

    public static final StringBuilder FIND_ALL_SUBJECT_QUERY = new StringBuilder("SELECT * FROM subjects");
    public static final StringBuilder DELETE_ALL_SUBJECT_QUERY = new StringBuilder("DELETE FROM subjects");

    //CREATE QUERIES

    public int addSubject(Subject subject, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("INSERT INTO subjects(title, description, enabled, genre_id, created_by, modified_by)");
        sql.append(" VALUES (:title,:description,:enabled,:genreId, :createdBy, :modifiedBy");
        sql.append(") RETURNING id");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", subject.getTitle());
        param.addValue("description", subject.getDescription());
        param.addValue("enabled", subject.isEnabled());
        param.addValue("genreId", subject.getGenreId());
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

    public Subject findById(int id){
        final String sql = "SELECT * FROM subjects WHERE id=:id";
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return (Subject) npJdbcTemplate.queryForObject(sql, param, new SubjectRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Subject findByTitle(String title){
        final String sql = "SELECT * FROM subjects WHERE title=:title";
        final SqlParameterSource param = new MapSqlParameterSource("title",title);
        try{
            return (Subject) npJdbcTemplate.queryForObject(sql, param, new SubjectRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Subject> findAll(){
        try{
            return npJdbcTemplate.query(FIND_ALL_SUBJECT_QUERY.toString(), new SubjectRowMapper());
        }catch (DataAccessException e){
            return null;
        }
    }

    public int findTotalCount(){
        final String sql = "SELECT count(*) from subjects;";
        try{
            Integer count = npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(),Integer.class);
            return count != null ? count : 0 ;
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    public List<Subject> findByGenreId(Integer genreId) {
        final String sql = "SELECT * FROM subjects WHERE genre_id=:genreId";
        final SqlParameterSource param = new MapSqlParameterSource("genreId",genreId);
        try{
            return npJdbcTemplate.query(sql, param, new SubjectRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public boolean subjectExistsByTitle(String title){
        final String sql = "SELECT EXISTS(SELECT 1 FROM subjects where title=:title)";
        MapSqlParameterSource param = new MapSqlParameterSource("title", title);
        try {
            return npJdbcTemplate.queryForObject(sql, param, Boolean.class);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    //DELETE QUERIES

    public boolean delete(int id) {
        final String sql = "DELETE FROM subjects WHERE id=:id";
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteByIds(List<Integer> ids){
        final String sql = "DELETE FROM subjects WHERE id in (:ids)";
        final SqlParameterSource param = new MapSqlParameterSource().addValue("ids", ids);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteAll(){
        final String sql = DELETE_ALL_SUBJECT_QUERY.toString();
        final SqlParameterSource param = new MapSqlParameterSource();
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    //UPDATE QUERIES

    public boolean updateSubject(int id, Subject subject, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("UPDATE subjects SET ");
        sql.append("title=:title,");
        sql.append("description=:description,");
        sql.append("enabled=:enabled,");
        sql.append("genre_id=:genreId, ");
        sql.append("modified_by=:loggedInUserId ");
        sql.append("WHERE id=:id");

        MapSqlParameterSource param = new MapSqlParameterSource("id",id)
                .addValue("title", subject.getTitle())
                .addValue("description", subject.getDescription())
                .addValue("enabled", subject.isEnabled())
                .addValue("genreId", subject.getGenreId())
                .addValue("id", id)
                .addValue("loggedInUserId", loggedInUserId);
        try{
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }
}
