package com.exam.category.repository.rowmappers;

import com.exam.category.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setTitle(rs.getString("title"));
        category.setDescription(rs.getString("description"));
        category.setEnabled(rs.getBoolean("enabled"));
        category.setSubjectId(rs.getInt("subject_id"));
        return category;
    }
}
