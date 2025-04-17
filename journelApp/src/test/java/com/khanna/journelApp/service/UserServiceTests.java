package com.khanna.journelApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khanna.journelApp.repositry.UserRepo;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;
    @Disabled
    @Test
    public void testFindByUserName(){
        assertEquals(4, 2+2);
        assertNotNull(userRepo.findByUserName("Khanna"));
    }
    @ParameterizedTest
    @CsvSource(
        {"1,1,2","1,2,3","2,2,4"}
    )
    public void test(int a,int b,int expected){
        assertEquals(expected, a+b);
    }
}
