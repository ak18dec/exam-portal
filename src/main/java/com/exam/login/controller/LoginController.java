package com.exam.login.controller;

import com.exam.auth.JwtUtill;
import com.exam.common.constant.ExceptionConstants;
import com.exam.login.exception.InvalidCredentialsException;
import com.exam.login.service.LoginService;
import com.exam.user.exception.UnregisteredEmailException;
import com.exam.user.exception.UserDisabledException;
import com.exam.login.model.JwtRequest;
import com.exam.login.model.JwtResponse;
import com.exam.user.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtill jwtUtill;

    @Autowired
    private LoginService loginService;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest request) throws Exception {

        authenticate(request.getUsernameOrEmail(), request.getPassword());

        //user is authenticated
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsernameOrEmail());
        String token = this.jwtUtill.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String usernameOrEmail, String password) throws UserDisabledException, InvalidCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usernameOrEmail, password));
        } catch (DisabledException e) {
            throw new DisabledException(ExceptionConstants.USER_IS_DISABLED + " : " + e.getMessage(), e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(ExceptionConstants.INVALID_CREDENTIALS);
        }
    }

    @GetMapping("/test")
    public String test() {
        return "Application is running.....";
    }
}
