package com.exam.account.controller;

import com.exam.account.model.ForgotPassword;
import com.exam.account.model.ResetPassword;
import com.exam.account.service.AccountService;
import com.exam.common.exception.TokenExpiredException;
import com.exam.user.exception.UserNotFoundException;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin("*")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPassword payload)
            throws MessagingException, UserNotFoundException, TemplateException, IOException {
        accountService.forgotPassword(payload.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/password-reset")
    public ResponseEntity<?> passwordReset(@Valid @RequestBody ResetPassword payload)
            throws MessagingException, TokenExpiredException, UserNotFoundException, TemplateException, IOException {
        accountService.resetPassword(payload);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
