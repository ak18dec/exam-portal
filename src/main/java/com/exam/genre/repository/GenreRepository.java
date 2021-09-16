package com.exam.genre.repository;

import com.exam.common.repository.BaseRepository;

import com.exam.genre.model.Genre;
import com.exam.genre.repository.rowmappers.GenreRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreRepository extends BaseRepository {



    public static final StringBuilder FIND_SINGLE_GENRE_QUERY = new StringBuilder("SELECT * FROM genre WHERE ");
    public static final StringBuilder FIND_ALL_GENRE_QUERY = new StringBuilder("SELECT * FROM genre");

    public static final StringBuilder DELETE_SINGLE_GENRE_QUERY = new StringBuilder("DELETE FROM genre WHERE ");
    public static final StringBuilder DELETE_ALL_GENRE_QUERY = new StringBuilder("DELETE FROM genre");
    public static final StringBuilder DELETE_LIST_OF_GENRE_QUERY = new StringBuilder("DELETE FROM genre WHERE ");

    //CREATE QUERIES

    public int addGenre(Genre genre){
        final StringBuilder sql = new StringBuilder("INSERT INTO genre(title, description, enabled");
        sql.append(" VALUES (:title,:description,:enabled");
        sql.append(")");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", genre.getTitle());
        param.addValue("description", genre.getDescription());
        param.addValue("enabled", genre.isEnabled());

        try{
            return npJdbcTemplate.update(sql.toString(), param);
        }catch (EmptyResultDataAccessException e){
            return -1;
        }
    }


    //SELECT QUERIES

    public Genre findById(int id){
        final String sql = FIND_SINGLE_GENRE_QUERY.append("id=:id").toString();
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return (Genre) npJdbcTemplate.queryForObject(sql, param, new GenreRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Genre findByTitle(String title){
        final String sql = FIND_SINGLE_GENRE_QUERY.append("title=:title").toString();
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
        final String sql = "SELECT count(*) from genre;";
        try{
            Integer count = npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(),Integer.class);
            return count != null ? count : 0 ;
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    public boolean genreExistsByTitle(String title){
        final String sql = "SELECT EXISTS(SELECT 1 FROM genre where title=:title)";
        MapSqlParameterSource param = new MapSqlParameterSource("title", title);
        try {
            return npJdbcTemplate.queryForObject(sql, param, Boolean.class);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    //DELETE QUERIES

    public boolean delete(int id) {
        final String sql = DELETE_SINGLE_GENRE_QUERY.append("id=:id").toString();
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteByIds(List<Integer> ids){
        final String sql = DELETE_LIST_OF_GENRE_QUERY.append("id in (:ids)").toString();
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

    public boolean updateGenre(int id, Genre genre){
        final StringBuilder sql = new StringBuilder("UPDATE genre SET ");
        sql.append("title=:title,");
        sql.append("description=:description,");
        sql.append("enabled=:enabled ");
        sql.append("WHERE id=:id");

        MapSqlParameterSource param = new MapSqlParameterSource("id",id)
                .addValue("title", genre.getTitle())
                .addValue("description", genre.getDescription())
                .addValue("enabled", genre.isEnabled())
                .addValue("id", id);
        try{
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }


}
