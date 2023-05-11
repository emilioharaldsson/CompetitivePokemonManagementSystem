package com.emilio.expenseapp.controller;

import com.emilio.expenseapp.dto.UserDTO;
import com.emilio.expenseapp.model.User;
import com.emilio.expenseapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/add")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        User newUser = new User();
        newUser.setFull_name(userDTO.getFull_name());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());

        User savedUser = userService.saveUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
