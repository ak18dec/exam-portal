package com.exam.user.service;

import com.exam.common.constant.ExceptionConstants;
import com.exam.user.exception.UserNotFoundException;
import com.exam.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = null;
        try {
            user = userService.getUserByUsername(s);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        if(user == null){
            throw new UsernameNotFoundException(ExceptionConstants.USER_NOT_FOUND_FOR_USERNAME +s);
        }

        return user;
    }
}
