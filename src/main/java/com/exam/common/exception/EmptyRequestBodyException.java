package com.exam.common.exception;

public class EmptyRequestBodyException extends RuntimeException {

    public EmptyRequestBodyException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyRequestBodyException(String message) {
        super(message);
    }
}
