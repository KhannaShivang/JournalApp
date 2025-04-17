package com.khanna.journelApp.service;


import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import com.khanna.journelApp.entity.User;
import com.khanna.journelApp.repositry.UserRepo;
import com.mongodb.assertions.Assertions;


public class UserDetailServiceImplTests {
    
    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Mock
    private UserRepo userRepositry;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void loadUserByUsernameTests(){
        Mockito.when(userRepositry.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Ram").password("jfieofnoe").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsServiceImpl.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
}
