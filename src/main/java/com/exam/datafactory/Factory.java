package com.exam.datafactory;

import com.exam.model.admin.Role;
import com.exam.model.admin.User;
import com.exam.model.admin.UserRole;
import com.exam.model.master.Genre;
import com.exam.model.master.Subject;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Factory {

    public static List<Role> roles = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Genre> genres = new ArrayList<>();
    public static List<Subject> subjects = new ArrayList<>();

    public static List<Role> createRoles(){
        roles.addAll(Arrays.asList(new Role(1L, "ADMIN"), new Role(2L, "BASIC")));
        return roles;
    }

    public static List<User> createUsers(){
        users.addAll(Arrays.asList(
            new User(1L,"ank123","abc123","Ankit","Kumar","demo1@gmail.com","1234567890",true,""),
            new User(2L,"harry12","xyz123","Harry","Potter","test1@gmail.com","1234567890",true,"")
        ));

        createUserRoles();

        return users;
    }

    private static void createUserRoles(){
        Set<UserRole> set0 = new HashSet<>();
        set0.add(new UserRole(1L, users.get(0).getId(), roles.get(0).getId()));

        Set<UserRole> set1 = new HashSet<>();
        set1.add(new UserRole(2L, users.get(1).getId(), roles.get(1).getId()));
    }

    public static List<Genre> createGenres(){
        genres.addAll(Arrays.asList(
                new Genre(1,"Technology","", true),
                new Genre(2,"Medical","",true),
                new Genre(3,"Arts","",true),
                new Genre(4,"Finance","",true)
        ));
        return genres;
    }

}
