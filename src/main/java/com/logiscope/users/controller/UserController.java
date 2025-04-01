package com.logiscope.users.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.logiscope.users.model.EmailRequest;
import com.logiscope.users.model.User;
import com.logiscope.users.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping //new user
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }
    
    
    @PostMapping("/bulk") //bulk user
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
        List<User> createdUsers = userService.createUsers(users);
        return ResponseEntity.status(201).body(createdUsers);
    }

//    @GetMapping("/{email}")
//    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
//        Optional<User> user = userService.getUserByEmail(email);
//        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
    
    
    @PostMapping("/getByEmail") //get single data
    public ResponseEntity<User> getUserByEmail(@RequestBody EmailRequest emailRequest) {
        Optional<User> user = userService.getUserByEmail(emailRequest.getEmail());
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    
    
    @GetMapping	//to get all data
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }
    
    

    @PutMapping	//to update data
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping	//to delete data
    public ResponseEntity<String> deleteUser(@RequestBody EmailRequest emailRequest) {
        userService.deleteUserByEmail(emailRequest.getEmail());
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
    
    @GetMapping("/{email}/password-expired")	 //to check expiry
    public ResponseEntity<Boolean> isPasswordExpired(@PathVariable String email) {
        boolean expired = userService.isPasswordExpired(email);
        return ResponseEntity.ok(expired);  // Returns true if expired, false if not
    }
    
    
}
