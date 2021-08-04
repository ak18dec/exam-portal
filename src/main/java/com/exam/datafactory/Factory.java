package com.exam.datafactory;

import com.exam.model.admin.Role;
import com.exam.model.admin.User;
import com.exam.model.admin.UserRole;
import com.exam.model.master.Genre;
import com.exam.model.master.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Factory {

    public static List<Role> roles = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Genre> genres = new ArrayList<>();
    public static List<Subject> subjects = new ArrayList<>();

    public static List<Role> getAllRoles(List<?> list){
        list.forEach(elem->roles.add((Role) elem));
        return roles;
    }

    public static List<User> getUsers(List<?> list){
        list.forEach(elem->users.add((User) elem));
        return users;
    }

}
