package com.exam.user.controller;

import com.exam.user.exception.UserNotFoundException;
import com.exam.user.model.User;
import com.exam.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    //GET API

    @GetMapping("/test")
    public String test(){
        return "Users API is up and running...";
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Integer id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping()
    public ResponseEntity<?> getUserByUsername(@RequestParam(value = "username") String username, @RequestParam(value = "email") String email) throws UserNotFoundException {
        User user = null;
        if(email != null && !email.isEmpty()){
            user = userService.getUserByEmail(email);
        }else {
            user = userService.getUserByUsername(username);
        }
        return ResponseEntity.ok(user);
    }


    //POST API

    @PostMapping("/")
    public User addUser(@RequestBody User user) throws Exception {
        return userService.createUser(user,1);
    }

    //PUT API

    @PutMapping("/{userId}")
    public boolean updateUser(@PathVariable("userId") int id, @RequestBody User user){
        return userService.updateUser(id, user, 1);
    }

    @PatchMapping("/{userId}")
    public boolean updateCredentials(@PathVariable("userId") int id,@RequestBody User user){
        final String username = user.getUsername();
        final String password = user.getPassword();
        return userService.updateCredentials(id, username, password,1);
    }

    @PatchMapping("/{id}/status/{status}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean updateUserStatus(@PathVariable("id") int id, @PathVariable("status") boolean status){
        return userService.updateUserStatus(id, status, 1);
    }

    //DELETE API

    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable("userId") Integer userId){
        return userService.deleteUser(userId);
    }

    @DeleteMapping("/")
    public boolean deleteAllUsers(){
        return userService.deleteAllUsers();
    }

}
