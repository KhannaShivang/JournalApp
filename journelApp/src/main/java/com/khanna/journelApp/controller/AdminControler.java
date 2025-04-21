package com.khanna.journelApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khanna.journelApp.cashe.AppCashe;
import com.khanna.journelApp.entity.User;
import com.khanna.journelApp.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("admin")
@Tag(name="Admin APIs")
public class AdminControler {
    
    @Autowired
    private UserService userService;
    @Autowired AppCashe appCashe;

    @GetMapping("all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.getAll();
        if(users != null && !users.isEmpty()){
            return new ResponseEntity<>(users,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("create-admin")
    public ResponseEntity<?>createAdmin(@RequestBody User user){
        userService.saveNewAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("clear-app-cache")
    public ResponseEntity<?> clearAppCache(){
        appCashe.init();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
