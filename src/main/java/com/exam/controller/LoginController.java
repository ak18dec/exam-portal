package com.exam.controller;

import com.exam.config.JwtUtill;
import com.exam.constant.ExceptionConstants;
import com.exam.exception.InvalidCredentialsException;
import com.exam.exception.UserDisabledException;
import com.exam.exception.UserNotFoundException;
import com.exam.model.security.JwtRequest;
import com.exam.model.security.JwtResponse;
import com.exam.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtill jwtUtill;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest request) throws Exception {

        authenticate(request.getUsername(), request.getPassword());

        //user is authenticated
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtUtill.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws UserDisabledException, InvalidCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException(ExceptionConstants.USER_IS_DISABLED + " : " + e.getMessage(), e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(ExceptionConstants.INVALID_CREDENTIALS);
        }
    }
}
