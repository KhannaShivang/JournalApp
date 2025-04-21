package com.khanna.journelApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khanna.journelApp.entity.User;
import com.khanna.journelApp.service.UserDetailsServiceImpl;
import com.khanna.journelApp.service.UserService;
import com.khanna.journelApp.utils.JWTUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/public")
@Tag(name="Public APIs")
public class PublicControler { 
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        userService.saveNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getUserName());
            String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwtToken,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Exception occures while creating JWT token",HttpStatus.BAD_REQUEST);
        }
    }
}
