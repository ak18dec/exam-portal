package com.exam.genre.exception;

public class GenreNotFoundException extends Exception {
    public GenreNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public GenreNotFoundException(String message) {
        super(message);
    }

}
