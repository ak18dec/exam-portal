package com.exam.quiz.repository.rowmappers;

import com.exam.quiz.model.Quiz;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        Quiz quiz = new Quiz();
        quiz.setId(rs.getInt("id"));
        quiz.setTitle(rs.getString("title"));
        quiz.setDescription(rs.getString("description"));
        quiz.setPublished(rs.getBoolean("published"));
        quiz.setProficiencyId(rs.getInt("proficiency_id"));
        quiz.setMaxMarks(rs.getInt("max_marks"));
        quiz.setMaxTime(rs.getInt("max_time"));
        quiz.setInstructionEnabled(rs.getBoolean("instruction_enabled"));
        return quiz;
    }
}
