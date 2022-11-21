package com.exam.account.model;
import com.exam.common.constant.RegexConstants;
import com.exam.common.constant.ValidationConstants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ForgotPassword {
    @Email(regexp = RegexConstants.EMAIL_REGEX, flags = Pattern.Flag.CASE_INSENSITIVE, message = ValidationConstants.EMAIL_VALIDATION)
    @NotBlank(message = ValidationConstants.EMAIL_REQUIRED)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
