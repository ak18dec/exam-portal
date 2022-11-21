package com.exam.otp.controller;

import com.exam.login.model.JwtResponse;
import com.exam.otp.exception.InvalidOTPException;
import com.exam.otp.exception.OTPNotFoundException;
import com.exam.otp.service.OTPService;
import com.exam.user.exception.UnregisteredEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@CrossOrigin("*")
public class OTPController {

    @Autowired
    private OTPService otpService;

    @GetMapping("/validate-otp")
    public ResponseEntity<?> validateOTP(@RequestParam("email") String email, @RequestParam("otp") int otp) throws OTPNotFoundException, InvalidOTPException {
        return ResponseEntity.ok(otpService.validateOTP(email, otp));
    }
}
