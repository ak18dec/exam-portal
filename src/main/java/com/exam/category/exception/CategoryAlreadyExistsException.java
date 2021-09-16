package com.exam.category.exception;

public class CategoryAlreadyExistsException extends Exception {

    public CategoryAlreadyExistsException(String message, Throwable cause){
        super(message, cause);
    }

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
