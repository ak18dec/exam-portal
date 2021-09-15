package com.exam.user.service;

import com.exam.common.constant.ExceptionConstants;
import com.exam.user.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = new User(1,"ank123","abc123","Ankit","Kumar","demo1@gmail.com","1234567890",true,"");

        if(user == null){
            throw new UsernameNotFoundException(ExceptionConstants.USER_NOT_FOUND+s);
        }

        return user;
    }
}
