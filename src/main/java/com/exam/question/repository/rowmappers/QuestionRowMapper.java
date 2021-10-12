package com.exam.question.repository.rowmappers;

import com.exam.question.model.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setTitle(rs.getString("title"));
        question.setDescription(rs.getString("description"));
        question.setEnabled(rs.getBoolean("enabled"));
        question.setProficiencyId(rs.getInt("proficiency_id"));
        question.setTopicId(rs.getInt("topic_id"));
        return question;
    }
}
