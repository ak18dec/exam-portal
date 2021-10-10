package com.exam.genre.repository;

import com.exam.common.repository.BaseRepository;

import com.exam.genre.model.Genre;
import com.exam.genre.repository.rowmappers.GenreRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreRepository extends BaseRepository {

    public static final StringBuilder FIND_ALL_GENRE_QUERY = new StringBuilder("SELECT * FROM genres");
    public static final StringBuilder DELETE_ALL_GENRE_QUERY = new StringBuilder("DELETE FROM genres");

    //CREATE QUERIES

    public int addGenre(Genre genre, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("INSERT INTO genres(title, description, enabled, created_by, modified_by)");
        sql.append(" VALUES (:title,:description,:enabled, :createdBy, :modifiedBy");
        sql.append(") RETURNING id");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", genre.getTitle());
        param.addValue("description", genre.getDescription());
        param.addValue("enabled", genre.isEnabled());
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

    public Genre findById(int id){
        final String sql = "SELECT * FROM genres WHERE id=:id";
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return (Genre) npJdbcTemplate.queryForObject(sql, param, new GenreRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Genre findByTitle(String title){
        final String sql = "SELECT * FROM genres WHERE title=:title";
        final SqlParameterSource param = new MapSqlParameterSource("title",title);
        try{
            return (Genre) npJdbcTemplate.queryForObject(sql, param, new GenreRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Genre> findAll(){
        try{
            return npJdbcTemplate.query(FIND_ALL_GENRE_QUERY.toString(), new GenreRowMapper());
        }catch (DataAccessException e){
            return null;
        }
    }

    public int findTotalCount(){
        final String sql = "SELECT count(*) from genres;";
        try{
            Integer count = npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(),Integer.class);
            return count != null ? count : 0 ;
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    public boolean genreExistsByTitle(String title){
        final String sql = "SELECT EXISTS(SELECT 1 FROM genres where title=:title)";
        MapSqlParameterSource param = new MapSqlParameterSource("title", title);
        try {
            return npJdbcTemplate.queryForObject(sql, param, Boolean.class);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    //DELETE QUERIES

    public boolean delete(int id) {
        final String sql = "DELETE FROM genres WHERE id=:id";
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteByIds(List<Integer> ids){
        final String sql = "DELETE FROM genres WHERE id in (:ids)";
        final SqlParameterSource param = new MapSqlParameterSource().addValue("ids", ids);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteAll(){
        final String sql = DELETE_ALL_GENRE_QUERY.toString();
        final SqlParameterSource param = new MapSqlParameterSource();
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    //UPDATE QUERIES

    public boolean updateGenre(int id, Genre genre, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("UPDATE genres SET ");
        sql.append("title=:title,");
        sql.append("description=:description,");
        sql.append("enabled=:enabled ");
        sql.append("modified_by=:loggedInUserId ");
        sql.append("WHERE id=:id");

        MapSqlParameterSource param = new MapSqlParameterSource("id",id)
                .addValue("title", genre.getTitle())
                .addValue("description", genre.getDescription())
                .addValue("enabled", genre.isEnabled())
                .addValue("id", id)
                .addValue("loggedInUserId", loggedInUserId);
        try{
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }
}
