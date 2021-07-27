package com.exam.controller;

import com.exam.exception.UserNotFoundException;
import com.exam.model.admin.User;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return "working fine...";
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user) throws Exception {
        return userService.createUser(user);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable("username") String username) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUser(username));
    }

    @PutMapping("/{username}")
    public boolean updateUser(@PathVariable("username") String username, @RequestBody User user){
        return userService.updateUser(username, user);
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable("userId") Long userId){
        return userService.deleteUser(userId);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> itemNotFEx(WebRequest webRequest, Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
