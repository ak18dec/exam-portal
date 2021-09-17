package com.exam.instruction.repository;

import com.exam.common.repository.BaseRepository;
import com.exam.instruction.model.Instruction;
import com.exam.instruction.repository.rowmappers.InstructionRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstructionRepository extends BaseRepository {

    public static final StringBuilder FIND_SINGLE_INSTRUCTION_QUERY = new StringBuilder("SELECT * FROM instruction WHERE ");
    public static final StringBuilder FIND_ALL_INSTRUCTION_QUERY = new StringBuilder("SELECT * FROM instruction");

    public static final StringBuilder DELETE_SINGLE_INSTRUCTION_QUERY = new StringBuilder("DELETE FROM instruction WHERE ");
    public static final StringBuilder DELETE_ALL_INSTRUCTION_QUERY = new StringBuilder("DELETE FROM instruction");
    public static final StringBuilder DELETE_LIST_OF_INSTRUCTION_QUERY = new StringBuilder("DELETE FROM instruction WHERE ");

    //CREATE QUERIES

    public int addInstruction(Instruction instruction){
        final StringBuilder sql = new StringBuilder("INSERT INTO instruction(content, enabled");
        sql.append(" VALUES (:content, :enabled");
        sql.append(")");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("content", instruction.getContent());
        param.addValue("enabled", instruction.isEnabled());

        try{
            return npJdbcTemplate.update(sql.toString(), param);
        }catch (EmptyResultDataAccessException e){
            return -1;
        }
    }


    //SELECT QUERIES

    public Instruction findById(int id){
        final String sql = FIND_SINGLE_INSTRUCTION_QUERY.append("id=:id").toString();
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return (Instruction) npJdbcTemplate.queryForObject(sql, param, new InstructionRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Instruction findByContent(String content){
        final String sql = FIND_SINGLE_INSTRUCTION_QUERY.append("content like %:content%").toString();
        final SqlParameterSource param = new MapSqlParameterSource("content",content);
        try{
            return (Instruction) npJdbcTemplate.queryForObject(sql, param, new InstructionRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Instruction> findAll(){
        try{
            return npJdbcTemplate.query(FIND_ALL_INSTRUCTION_QUERY.toString(), new InstructionRowMapper());
        }catch (DataAccessException e){
            return null;
        }
    }

    public int findTotalCount(){
        final String sql = "SELECT count(*) from instruction;";
        try{
            Integer count = npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(),Integer.class);
            return count != null ? count : 0 ;
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    public boolean instructionExistsByContent(String content){
        final String sql = "SELECT EXISTS(SELECT 1 FROM instruction where content=:content)";
        MapSqlParameterSource param = new MapSqlParameterSource("content", content);
        try {
            return npJdbcTemplate.queryForObject(sql, param, Boolean.class);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    //DELETE QUERIES

    public boolean delete(int id) {
        final String sql = DELETE_SINGLE_INSTRUCTION_QUERY.append("id=:id").toString();
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteByIds(List<Integer> ids){
        final String sql = DELETE_LIST_OF_INSTRUCTION_QUERY.append("id in (:ids)").toString();
        final SqlParameterSource param = new MapSqlParameterSource().addValue("ids", ids);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteAll(){
        final String sql = DELETE_ALL_INSTRUCTION_QUERY.toString();
        final SqlParameterSource param = new MapSqlParameterSource();
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    //UPDATE QUERIES

    public boolean updateInstruction(int id, Instruction instruction){
        final StringBuilder sql = new StringBuilder("UPDATE instruction SET ");
        sql.append("content=:content,");
        sql.append("enabled=:enabled ");
        sql.append("WHERE id=:id");

        MapSqlParameterSource param = new MapSqlParameterSource("id",id)
                .addValue("content", instruction.getContent())
                .addValue("enabled", instruction.isEnabled())
                .addValue("id", id);
        try{
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }


}
