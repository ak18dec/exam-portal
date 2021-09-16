package com.exam.genre.exception;

public class GenreAlreadyExistsException extends Exception {

    public GenreAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenreAlreadyExistsException(String message) {
        super(message);
    }
}
