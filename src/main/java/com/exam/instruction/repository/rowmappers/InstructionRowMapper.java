package com.exam.instruction.repository.rowmappers;

import com.exam.instruction.model.Instruction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructionRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        Instruction instruction = new Instruction();
        instruction.setId(rs.getInt("id"));
        instruction.setContent(rs.getString("content"));
        instruction.setEnabled(rs.getBoolean("enabled"));
        return instruction;
    }
}
