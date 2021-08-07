package com.exam.controller;

import com.exam.exception.UserNotFoundException;
import com.exam.model.admin.User;
import com.exam.service.UserService;
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
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping()
    public ResponseEntity<?> getUserByUsername(@RequestParam(value = "username") String username) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    //POST API

    @PostMapping("/")
    public User addUser(@RequestBody User user) throws Exception {
        return userService.createUser(user,1);
    }

    //PUT API

    @PutMapping("/{userId}")
    public boolean updateUser(@PathVariable("id") int id, @RequestBody User user){
        return userService.updateUser(id, user, 1);
    }

    @PatchMapping("/{userId}")
    public boolean updateCredentials(@PathVariable("id") int id,@RequestBody User user){
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
