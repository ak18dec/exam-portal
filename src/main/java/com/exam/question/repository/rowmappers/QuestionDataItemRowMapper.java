package com.exam.question.repository.rowmappers;


import com.exam.question.model.QuestionChoice;
import com.exam.question.model.QuestionDataItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDataItemRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        QuestionDataItem questionDataItem = new QuestionDataItem();
        questionDataItem.setId(rs.getInt("id"));
        questionDataItem.setDescription(rs.getString("description"));
        questionDataItem.setEnabled(rs.getBoolean("enabled"));
        questionDataItem.setProficiency(rs.getString("proficiency"));
        questionDataItem.setTopicId(rs.getInt("topic_id"));

        QuestionChoice questionChoice = new QuestionChoice();
        questionChoice.setId(rs.getInt("qc_id"));
        questionChoice.setDescription(rs.getString("qc_desc"));
        questionChoice.setEnabled(rs.getBoolean("qc_enabled"));
        questionChoice.setCorrect(rs.getBoolean("qc_correct"));

        questionDataItem.setQuestionChoice(questionChoice);

        return questionDataItem;
    }
}
