package com.exam.otp.repository;

import com.exam.common.constant.ExceptionConstants;
import com.exam.common.exception.InvalidTokenException;
import com.exam.common.exception.TokenExpiredException;
import com.exam.common.repository.BaseRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;

import static com.exam.common.constant.ValidationConstants.TOKEN_EXPIRE_TIME_MILLI_SECONDS;


@Repository
public class OTPRepository extends BaseRepository {

    public int addOtp(String email, int otp) {
        final StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO users_otp(email, otp, used) ");
        sql.append(" VALUES (:email, :otp, :used)");
        sql.append(" RETURNING id");

        final MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("email", email);
        param.addValue("otp", otp);
        param.addValue("used", false);
        try {
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            npJdbcTemplate.update(sql.toString(), param, generatedKeyHolder);
            return generatedKeyHolder.getKey().intValue();
        }catch (EmptyResultDataAccessException e) {
            return -1;
        }
    }

    public Date findOtpCreationTime(String email) {
        final String sql = "select created_on from users_otp uo where email=:email and used=:used order by created_on desc limit 1";

        final MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("email", email);
        param.addValue("used", false);

        try {
            return npJdbcTemplate.queryForObject(sql, param, Date.class);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void addToken(String email, String token) {
    }

    public Date findTokenCreationTime(String email) {
        return new Date();
    }

    public boolean validateToken(String token) throws InvalidTokenException, TokenExpiredException {
        final String sql =  "select created_on, used from users_otp where token=:token";
        final MapSqlParameterSource param = new MapSqlParameterSource("token", token);
        Object[] data = null;
        try {
            data = npJdbcTemplate.queryForObject(sql, param, Object[].class);
        } catch (EmptyResultDataAccessException e) {
            throw new InvalidTokenException(ExceptionConstants.INVALID_TOKEN);
        }

        Date tokenCreationTime = (Date) data[0];

        if((tokenCreationTime.getTime() + TOKEN_EXPIRE_TIME_MILLI_SECONDS) < System.currentTimeMillis()){
            throw new TokenExpiredException(ExceptionConstants.TOKEN_EXPIRED);
        }else return !((Boolean) data[1]); // false if token already used
    }

    public String findEmailByToken(String token) throws InvalidTokenException {
        final String sql = "select email from users_otp where token=:token order by id desc limit 1";
        final MapSqlParameterSource param = new MapSqlParameterSource("token", token);
        try {
            return npJdbcTemplate.queryForObject(sql, param, String.class);
        }catch (EmptyResultDataAccessException e) {
            throw new InvalidTokenException(ExceptionConstants.INVALID_TOKEN);
        }
    }
}
