package com.exam.instruction.repository;

import com.exam.common.repository.BaseRepository;
import com.exam.instruction.model.Instruction;
import com.exam.instruction.repository.rowmappers.InstructionRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstructionRepository extends BaseRepository {

    public static final StringBuilder FIND_ALL_INSTRUCTION_QUERY = new StringBuilder("SELECT * FROM instructions");
    public static final StringBuilder DELETE_ALL_INSTRUCTION_QUERY = new StringBuilder("DELETE FROM instructions");

    //CREATE QUERIES

    public int addInstruction(Instruction instruction, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("INSERT INTO instructions(content, enabled, created_by, modified_by)");
        sql.append(" VALUES (:content, :enabled, :createdBy, :modifiedBy");
        sql.append(") RETURNING id");

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("content", instruction.getContent());
        param.addValue("enabled", instruction.isEnabled());
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

    public Instruction findById(int id){
        final String sql = "SELECT * FROM instructions WHERE id=:id";
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return (Instruction) npJdbcTemplate.queryForObject(sql, param, new InstructionRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Instruction findByContent(String content){
        final String sql = "SELECT * FROM instructions WHERE content like %:content%";
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
        final String sql = "SELECT count(*) from instructions;";
        try{
            Integer count = npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(),Integer.class);
            return count != null ? count : 0 ;
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    public boolean instructionExistsByContent(String content){
        final String sql = "SELECT EXISTS(SELECT 1 FROM instructions where content=:content)";
        MapSqlParameterSource param = new MapSqlParameterSource("content", content);
        try {
            return npJdbcTemplate.queryForObject(sql, param, Boolean.class);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    //DELETE QUERIES

    public boolean delete(int id) {
        final String sql = "DELETE FROM instructions WHERE id=:id";
        final SqlParameterSource param = new MapSqlParameterSource("id",id);
        try{
            return npJdbcTemplate.update(sql, param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteByIds(List<Integer> ids){
        final String sql = "DELETE FROM instructions WHERE id in (:ids)";
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

    public boolean updateInstruction(int id, Instruction instruction, int loggedInUserId){
        final StringBuilder sql = new StringBuilder("UPDATE instructions SET ");
        sql.append("content=:content,");
        sql.append("enabled=:enabled ");
        sql.append("modified_by=:loggedInUserId ");
        sql.append("WHERE id=:id");

        MapSqlParameterSource param = new MapSqlParameterSource("id",id)
                .addValue("content", instruction.getContent())
                .addValue("enabled", instruction.isEnabled())
                .addValue("id", id)
                .addValue("loggedInUserId", loggedInUserId);
        try{
            return npJdbcTemplate.update(sql.toString(), param) > 0;
        }catch (DataAccessException e){
            return false;
        }
    }


}
