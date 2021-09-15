package com.exam.admin.service;

import com.exam.user.model.Role;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.exam.datafactory.Factory.*;

@Service
public class AdminService {

    public static Map<Integer, String> roleMap = new HashMap<>();

    public List<Role> getRoles(){
        roleMap.clear();
        roles.forEach(role -> roleMap.put(role.getId(), role.getRoleName()));
        return roles;
    }

}
