package com.exam.genre.repository.rowmappers;

import com.exam.genre.model.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        Genre genre = new Genre();
        genre.setId(rs.getInt("id"));
        genre.setTitle(rs.getString("title"));
        genre.setDescription(rs.getString("description"));
        genre.setEnabled(rs.getBoolean("enabled"));
        return genre;
    }
}
