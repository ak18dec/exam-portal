package com.exam.account.model;

import com.exam.common.constant.ExceptionConstants;
import com.exam.common.constant.ValidationConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ResetPassword {
    @NotBlank(message = ValidationConstants.PASSWORD_REQUIRED)
    @Size(message = ValidationConstants.PASSWORD_LENGTH, min = ValidationConstants.PASSWORD_MIN_LENGTH, max = ValidationConstants.PASSWORD_MAX_LENGTH)
    private String newPassword;

    @NotBlank(message = ValidationConstants.PASSWORD_REQUIRED)
    @Size(message = ValidationConstants.PASSWORD_LENGTH, min = ValidationConstants.PASSWORD_MIN_LENGTH, max = ValidationConstants.PASSWORD_MAX_LENGTH)
    private String confirmPassword;

    private String token;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean passwordMatches() {
        if(newPassword.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }
        return newPassword.equals(confirmPassword);
    }
}
