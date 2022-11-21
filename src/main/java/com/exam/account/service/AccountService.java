package com.exam.account.service;

import com.exam.account.model.ResetPassword;
import com.exam.common.exception.InvalidTokenException;
import com.exam.common.exception.TokenExpiredException;
import com.exam.notification.email.model.MailRequest;
import com.exam.notification.email.service.EmailService;
import com.exam.otp.service.OTPService;
import com.exam.user.exception.UserNotFoundException;
import com.exam.user.model.User;
import com.exam.user.service.UserService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private UserService userService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private EmailService emailService;

    public void forgotPassword(String email) throws MessagingException, UserNotFoundException, TemplateException, IOException {
        User user = userService.getUserByEmail(email);
        boolean previousTokenExpired = otpService.tokenExpired(email);
        if(user != null && previousTokenExpired) {
            String token = otpService.generateToken();
            System.out.println("Generated Token: "+ token);

            final String passwordResetUrl = generatePasswordResetUrl(token);

            final String subject = "Reset Your Exam Portal Password - Expire in 5 minutes!";

            MailRequest request = new MailRequest();
            request.setName(user.getFirstName());
            request.setRecipient(user.getEmail());
            request.setSubject(subject);

            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("Name", user.getFirstName());
            dataModel.put("PasswordResetUrl", passwordResetUrl);
            dataModel.put("OperatingSystem", "Mac OS");
            dataModel.put("BrowserName", "Chrome Browser");

            emailService.sendTemplatedEmail(request, dataModel, EmailService.EmailType.PASSWORD_RESET);
            otpService.savePasswordResetToken(email, token);
        }
    }


    private String generatePasswordResetUrl(String token) {
        final String baseUrl = "http://localhost:4200";
        final String passwordResetSubUrl = "/password-reset/";
        return baseUrl + passwordResetSubUrl + token;
    }

    public void resetPassword(ResetPassword payload) throws InvalidTokenException, TokenExpiredException, UserNotFoundException, MessagingException, TemplateException, IOException {
        final String token = payload.getToken();
        final boolean validToken = otpService.validateToken(token);

        if(validToken && payload.passwordMatches()) {
            final String email = findEmailByToken(token);
            final User user = userService.getUserByEmail(email);
            userService.updateCredentials(user.getId(), user.getUsername(), payload.getNewPassword(), user.getId());

            final String subject = "Your Exam Portal password has been changed";
//            final String content = "This is a confirmation that the password for your Exam Portal account "+user.getUsername()+" has just been changed.";

            MailRequest request = new MailRequest();
            request.setName(user.getFirstName());
            request.setRecipient(user.getEmail());
            request.setSubject(subject);

            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("Name", user.getFirstName());
            dataModel.put("Username", user.getUsername());
            dataModel.put("OperatingSystem", "Mac OS");
            dataModel.put("BrowserName", "Chrome Browser");

            emailService.sendTemplatedEmail(request, dataModel, EmailService.EmailType.PASSWORD_UPDATED);
        }
    }

    private String findEmailByToken(String token) throws InvalidTokenException {
        return otpService.findEmailByToken(token);
    }
}
