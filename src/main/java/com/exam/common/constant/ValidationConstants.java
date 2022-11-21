package com.exam.common.constant;

public class ValidationConstants {

    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 16;
    public static final String PASSWORD_REQUIRED = "Password is a required field and can not be left empty";
    public static final String PASSWORD_LENGTH = "Password must be equal to or greater than 8 characters and less than 16 characters";

    public static final String EMAIL_REQUIRED = "Email can not be left empty";
    public static final String EMAIL_VALIDATION = "Please provide email in proper format";

    public static final Integer TOKEN_EXPIRE_TIME_MILLI_SECONDS = 300000;

}
