package com.exam.otp.exception;

public class OTPNotFoundException extends RuntimeException{

    public OTPNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OTPNotFoundException(String message) {
        super(message);
    }
}
