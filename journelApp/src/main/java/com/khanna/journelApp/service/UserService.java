package com.khanna.journelApp.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.khanna.journelApp.entity.User;
import com.khanna.journelApp.repositry.UserRepo;


@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;

    public void saveUser(User user){
        userRepo.save(user);
    }
    public void saveNewUser(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        user.setDate(LocalDateTime.now());
        userRepo.save(user);
    }
    public void saveNewAdmin(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        user.setDate(LocalDateTime.now());
        userRepo.save(user);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }
    public Optional<User> findById(ObjectId id){
        return userRepo.findById(id);
    }
    public void deleteById(ObjectId id){
        userRepo.deleteById(id);
    }
    public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }
    public void deleteByUserName(String userName){
        userRepo.deleteByUserName(userName);
    }
}
