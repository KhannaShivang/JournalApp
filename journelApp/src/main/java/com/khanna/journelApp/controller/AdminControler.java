package com.khanna.journelApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khanna.journelApp.entity.User;
import com.khanna.journelApp.service.UserService;

@RestController
@RequestMapping("admin")
public class AdminControler {
    
    @Autowired
    private UserService userService;

    @GetMapping("all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.getAll();
        if(users != null && !users.isEmpty()){
            return new ResponseEntity<>(users,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
