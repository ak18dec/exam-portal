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
    public static final String UNAUTHORIZED_REQUEST = "Unauthorized request";
    public static final String INVALID_TOKEN_NOT_START_WITH_BEARER = "Invalid token, not start with bearer string";
    public static final String INVALID_TOKEN = "Token is not valid";
    public static final String USER_IS_DISABLED = "User is disabled";
}
