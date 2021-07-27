package com.exam.exception;

public class TokenExpiredException extends Exception{

    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenExpiredException(String message) {
        super(message);
    }
}
