package com.demo.expensetrackerapi.controller;


import com.demo.expensetrackerapi.entity.User;
import com.demo.expensetrackerapi.entity.UserModel;
import com.demo.expensetrackerapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel userModel){
        UserModel createdUser = userService.createUser(userModel);
        return new ResponseEntity<UserModel>(userModel, HttpStatus.CREATED);
    }

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    
}
