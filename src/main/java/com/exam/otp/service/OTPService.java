package com.exam.otp.service;

import com.exam.common.exception.InvalidTokenException;
import com.exam.common.exception.TokenExpiredException;
import com.exam.otp.exception.InvalidOTPException;
import com.exam.otp.exception.OTPNotFoundException;
import com.exam.otp.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OTPService {

    private final static int OTP_EXPIRE_TIME_MILI_SECONDS = 300000;

    @Autowired
    private OTPRepository otpRepository;

    public int generateOTP() {
        final int otp =  ThreadLocalRandom.current().nextInt(100000, 999999);
        return otp;
    }

    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    public boolean tokenExpired(String email) {
//        final Date tokenCreationTime =  otpRepository.findTokenCreationTime(email);
//        return tokenCreationTime != null && ((tokenCreationTime.getTime() + OTP_EXPIRE_TIME_MILI_SECONDS) < System.currentTimeMillis());
        return true;
    }

    public void saveOtp(String email, int otp) {
        otpRepository.addOtp(email, otp);
    }

    public void savePasswordResetToken(String email, String token) {
        otpRepository.addToken(email, token);
    }

    public boolean validateOTP(String email, int otp) throws OTPNotFoundException, InvalidOTPException {
//        final Date otpCreationTime = otpRepository.findOtpCreationTime(email, otp);
//        if(otpCreationTime == null) {
//            throw new OTPNotFoundException(ExceptionConstants.OTP_NOT_FOUND);
//        }
//        if(otpCreationTime.getTime() + OTP_EXPIRE_TIME_MILI_SECONDS < System.currentTimeMillis()) {
//            throw new InvalidOTPException(ExceptionConstants.INVALID_OTP);
//        }
        return true;
    }

    public boolean otpExpired(String email) {
        final Date otpCreationTime = otpRepository.findOtpCreationTime(email);
        return otpCreationTime != null && ((otpCreationTime.getTime() + OTP_EXPIRE_TIME_MILI_SECONDS) < System.currentTimeMillis());
    }

    public boolean validateToken(String token) throws InvalidTokenException, TokenExpiredException {
        return otpRepository.validateToken(token);
    }

    public String findEmailByToken(String token) throws InvalidTokenException {
        return otpRepository.findEmailByToken(token);
    }
}