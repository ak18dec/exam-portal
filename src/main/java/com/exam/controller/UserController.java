package com.exam.controller;

import com.exam.model.admin.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @GetMapping("/test")
    public String test(){
        return "working fine...";
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return null;
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user){
        return null;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable("username") String username) {
        return null;
    }

    @PutMapping("/{username}")
    public boolean updateUser(@PathVariable("username") String username, @RequestBody User user){
        return false;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> itemNotFEx(WebRequest webRequest, Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
