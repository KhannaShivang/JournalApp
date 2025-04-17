package com.khanna.journelApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khanna.journelApp.service.UserService;
import com.khanna.journelApp.service.WeatherService;
import com.khanna.journelApp.api.response.WeatherResponse;
import com.khanna.journelApp.entity.User;

@RestController
@RequestMapping("/User")
public class UserControler {
    
    @Autowired
    private UserService userService;
    @Autowired
    private WeatherService weatherService;

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        userService.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> updateUserById(@RequestBody User user){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User myUser = userService.findByUserName(userName);
        myUser.setUserName(user.getUserName());
        myUser.setPassword(user.getPassword());
        userService.saveNewUser(myUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getWeather(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weather = weatherService.getWeather("Dehradun");

        String greating="";
        if(weather!=null){
            greating = "Temperature is: "+weather.getCurrent().getTemperature()+"°C"+weather.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi"+auth.getName()+" "+greating,HttpStatus.OK);
    }
}
