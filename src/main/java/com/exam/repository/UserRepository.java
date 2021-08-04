package com.exam.repository;

import com.exam.model.admin.User;
import com.exam.repository.rowmappers.UserRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends BaseRepository{

    public static final StringBuilder FIND_SINGLE_USER_QUERY = new StringBuilder("SELECT * FROM users WHERE ");
    public static final StringBuilder FIND_ALL_USER_QUERY = new StringBuilder("");

    public User findById(int id){
        String sql = FIND_SINGLE_USER_QUERY.append("id=?").toString();
        try{
            return (User) jdbcTemplate.queryForObject(sql, new UserRowMapper(),new Object[]{ id });
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public User findByUsername(String username){
        String sql = FIND_SINGLE_USER_QUERY.append("username=?").toString();
        try{
            return (User) jdbcTemplate.queryForObject(sql, new UserRowMapper(),new Object[]{ username });
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public User findByEmail(String email){
        String sql = FIND_SINGLE_USER_QUERY.append("email=?").toString();
        try{
            return (User) jdbcTemplate.queryForObject(sql, new UserRowMapper(),new Object[]{ email });
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public User findByName(String name){
        String sql = FIND_SINGLE_USER_QUERY.append("first_name=? OR last_name=?").toString();
        try{
//            return (User) jdbcTemplate.queryForObject(sql, new UserRowMapper(),new Object[]{ email });
        }catch (EmptyResultDataAccessException e){
            return null;
        }

        return null;
    }

    public boolean delete(int id) {
        return false;
    }

    public boolean deleteByIds(List<Integer> ids){
        return false;
    }

    public boolean deleteAll(){
        return false;
    }

    public boolean update(int id, User user){
        return false;
    }

    public List<User> findAll(){
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public int findTotalCount(){
        return 0;
    }
}
