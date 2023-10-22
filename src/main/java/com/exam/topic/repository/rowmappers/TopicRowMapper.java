package com.exam.topic.repository.rowmappers;

import com.exam.topic.model.Topic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        Topic topic = new Topic();
        topic.setId(rs.getInt("id"));
        topic.setTitle(rs.getString("title"));
        topic.setEnabled(rs.getBoolean("enabled"));
        topic.setSubjectId(rs.getInt("subject_id"));
        return topic;
    }
}
