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
        question.setDescription(rs.getString("content"));
        question.setEnabled(rs.getBoolean("enabled"));
        question.setProficiency(rs.getString("proficiency"));
        question.setTopicId(rs.getInt("topic_id"));
        return question;
    }
}
