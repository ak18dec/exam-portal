package com.exam.service;

import com.exam.datafactory.Factory;
import com.exam.model.admin.Role;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exam.datafactory.Factory.*;

@Service
public class AdminService {

    public List<Role> getRoles(){
        return roles;
    }
}
