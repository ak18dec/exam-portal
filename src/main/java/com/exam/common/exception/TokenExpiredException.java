package com.exam.common.exception;

public class TokenExpiredException extends Exception{

    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenExpiredException(String message) {
        super(message);
    }
}
