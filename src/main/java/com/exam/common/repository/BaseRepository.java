package com.exam.common.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseRepository {

    @Autowired
    public NamedParameterJdbcTemplate npJdbcTemplate;
}
