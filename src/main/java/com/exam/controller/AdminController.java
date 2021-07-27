package com.exam.controller;

import com.exam.model.admin.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static  com.exam.datafactory.Factory.roles;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roles;
    }
}
