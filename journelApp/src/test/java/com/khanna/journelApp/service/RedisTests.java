package com.khanna.journelApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void redisTest(){
        redisTemplate.opsForValue().set("email","shivang@gmail.com");
        String mail = redisTemplate.opsForValue().get("name");
        assertEquals(mail, "shivang@gmail.com");
    }
}
