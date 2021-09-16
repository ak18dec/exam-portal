package com.exam.user.repository;

import com.exam.common.repository.BaseRepository;
import com.exam.user.model.User;
import com.exam.user.repository.rowmappers.UserRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends BaseRepository {

    public static final StringBuilder FIND_SINGLE_USER_QUERY = new StringBuilder("SELECT * FROM users WHERE ");
    public static final StringBuilder FIND_ALL_USERS_QUERY = new StringBuilder("SELECT * FROM users");

    public static final StringBuilder DELETE_SINGLE_USER_QUERY = new StringBuilder("DELETE FROM users WHERE ");
    public static final StringBuilder DELETE_ALL_USERS_QUERY = new StringBuilder("DELETE FROM users");
    public static final StringBuilder DELETE_LIST_OF_USERS_QUERY = new StringBuilder("DELETE FROM users WHERE ");

    //CREATE QUERIES

    public int addUser(User user, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("INSERT INTO users(username, password, first_name, last_name");
        sql.append(", email, phone, enabled, profile, created_by, modified_by ");
        sql.append(" VALUES (:username,:password,:firstName,:lastName,:email, :phone");
        sql.append(",:enabled, :profile, :createdBy,:modifiedBy");
        sql.append(")");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("username", user.getUsername());
        param.addValue("password", user.getPassword());
        param.addValue("firstName", user.getFirstName());
        param.addValue("lastName", user.getLastName());
        param.addValue("email", user.getEmail());
        param.addValue("phone", user.getPhone());
        param.addValue("enabled", user.isEnabled());
        param.addValue("profile", user.getProfile());
        param.addValue("createdBy", loggedInUserId);
        param.addValue("modifiedBy", loggedInUserId);

        try{
            return npJdbcTemplate.update(sql.toString(), param);
        }catch (EmptyResultDataAccessException e){
            return -1;
        }
    }


    //SELECT QUERIES

    public User findById(int id){
        final String sql = FIND_SINGLE_USER_QUERY.append("id=:id").toString();
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return (User) npJdbcTemplate.queryForObject(sql, param, new UserRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public User findByUsername(String username){
        final String sql = FIND_SINGLE_USER_QUERY.append("username=:username").toString();
        final SqlParameterSource param = new MapSqlParameterSource("username",username);
        try{
            return (User) npJdbcTemplate.queryForObject(sql, param, new UserRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public boolean userExistsByUsername(String username){
        final String sql = "SELECT EXISTS(SELECT 1 FROM users where username=:username)";
        MapSqlParameterSource param = new MapSqlParameterSource("username", username);
        try {
            return npJdbcTemplate.queryForObject(sql, param, Boolean.class);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    public User findByEmail(String email){
        final String sql = FIND_SINGLE_USER_QUERY.append("email=:email").toString();
        final SqlParameterSource param = new MapSqlParameterSource("email",email);
        try{
            return (User) npJdbcTemplate.queryForObject(sql, param, new UserRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public User findByName(String name){
        final String sql = FIND_SINGLE_USER_QUERY.append("first_name=:name OR last_name=:name").toString();
        final SqlParameterSource param = new MapSqlParameterSource("name",name);
        try{
            return (User) npJdbcTemplate.queryForObject(sql, param, new UserRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<User> findAll(){
        try{
            return npJdbcTemplate.query(FIND_ALL_USERS_QUERY.toString(), new UserRowMapper());
        }catch (DataAccessException e){
            return null;
        }
    }

    public int findTotalCount(){
        final String sql = "SELECT count(*) from users;";
        try{
            Integer count = npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(),Integer.class);
            return count != null ? count : 0 ;
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    //DELETE QUERIES

    public boolean delete(int id) {
        final String sql = DELETE_SINGLE_USER_QUERY.append("id=:id").toString();
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteByIds(List<Integer> ids){
        final String sql = DELETE_LIST_OF_USERS_QUERY.append("id in (:ids)").toString();
        final SqlParameterSource param = new MapSqlParameterSource().addValue("ids", ids);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteAll(){
        final String sql = DELETE_ALL_USERS_QUERY.toString();
        final SqlParameterSource param = new MapSqlParameterSource();
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    //UPDATE QUERIES

    //accessible to Basic Users
    public boolean basicUpdate(int id, User user, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("UPDATE users SET ");
        sql.append("first_name=:firstName,");
        sql.append("last_name=:lastName,");
        sql.append("email=:email,");
        sql.append("phone=:phone,");
        sql.append("modified_by=:loggedInUserId ");
        sql.append("WHERE id=:id");

        MapSqlParameterSource param = new MapSqlParameterSource("id",id)
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("phone", user.getPhone())
                .addValue("profile", user.getProfile())
                .addValue("id", id)
                .addValue("loggedInUserId", loggedInUserId);
        try{
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean updateCredentials(int id, String username, String password, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("UPDATE users SET modified_by=:loggedInUserId, ");
        final MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id",id);
        param.addValue("loggedInUserId", loggedInUserId);

        boolean flag = false;

        if(username != null && !username.isEmpty()){
            sql.append("username=:username");
            param.addValue("username", username);
            flag = true;
        }
        if(password != null && !password.isEmpty()){

            sql.append(flag ? "," : "");

            sql.append("password=:password");
            param.addValue("password", password);
        }

        sql.append(" WHERE id=:id");

        try{
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    //accessible to only ADMINS
    public boolean updateUserStatus(int id, boolean status, int loggedInUserId){
        final String sql = "UPDATE users SET enabled=:enabled, modified_by=:loggedInUserId WHERE id=:id";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id",id);
        param.addValue("enabled", status);
        param.addValue("loggedInUserId", loggedInUserId);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }
}
