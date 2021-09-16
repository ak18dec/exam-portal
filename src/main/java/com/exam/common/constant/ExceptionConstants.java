package com.exam.common.constant;

//this class is final to prevent it from being extended
public final class ExceptionConstants {

    //constructor is private so that the class can't be instantiated
    private ExceptionConstants(){
    }

    public static final String USER_NOT_FOUND_FOR_USERNAME = "User not found for username: ";
    public static final String USER_NOT_FOUND_FOR_ID = "User not found for id: ";
    public static final String USER_ALREADY_EXISTS = "User already exists with username: ";
    public static final String TOKEN_EXPIRED = "Token has expired";
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String UNAUTHORIZED_REQUEST = "Unauthorized request";
    public static final String INVALID_TOKEN_NOT_START_WITH_BEARER = "Invalid token, not start with bearer string";
    public static final String INVALID_TOKEN = "Token is not valid";
    public static final String USER_IS_DISABLED = "User is disabled";
    public static final String NO_RECORD_FOUND = "No record found";

    public static final String GENRE_NOT_FOUND_FOR_ID = "Genre not found for id: ";
    public static final String GENRE_NOT_FOUND_FOR_TITLE = "Genre not found for title: ";
    public static final String GENRE_ALREADY_EXISTS = "Genre already exists with title: ";

    public static final String SUBJECT_NOT_FOUND_FOR_ID = "Subject not found for id: ";
    public static final String SUBJECT_NOT_FOUND_FOR_TITLE = "Subject not found for title: ";
    public static final String SUBJECT_NOT_FOUND_FOR_GENRE_ID = "Subject not found for genreId: ";
    public static final String SUBJECT_ALREADY_EXISTS = "Subject already exists with title: ";

    public static final String CATEGORY_NOT_FOUND_FOR_ID = "Category not found for id: ";
    public static final String CATEGORY_NOT_FOUND_FOR_TITLE = "Category not found for title: ";
    public static final String CATEGORY_NOT_FOUND_FOR_SUBJECT_ID = "Category not found for subjectId: ";
    public static final String CATEGORY_ALREADY_EXISTS = "Category already exists with title: ";
}
