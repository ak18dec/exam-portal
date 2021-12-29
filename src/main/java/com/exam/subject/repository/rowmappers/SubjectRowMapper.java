package com.exam.subject.repository.rowmappers;

import com.exam.subject.model.Subject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getInt("id"));
        subject.setTitle(rs.getString("title"));
        subject.setDescription(rs.getString("description"));
//        subject.setGenreId(rs.getInt("genre_id"));
        subject.setEnabled(rs.getBoolean("enabled"));
        return subject;
    }
}
