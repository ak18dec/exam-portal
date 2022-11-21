package com.exam.common.exception;

public class InvalidEmailFormatException extends RuntimeException{

    public InvalidEmailFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEmailFormatException(String message) {
        super(message);
    }

}
