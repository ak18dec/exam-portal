package com.exam.constant;

//this class is final to prevent it from being extended
public final class ExceptionConstants {

    //constructor is private so that the class can't be instantiated
    private ExceptionConstants(){
    }

    public static final String USER_NOT_FOUND = "User not found with username: ";
    public static final String USER_ALREADY_EXISTS = "User already exists with username: ";
    public static final String TOKEN_EXPIRED = "Token has expired";
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
}
