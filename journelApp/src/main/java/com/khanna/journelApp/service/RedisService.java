package com.khanna.journelApp.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisService {
    
    @Autowired
    RedisTemplate redisTemplate;
    public<T> T get(String key, Class<T> entityClass){
        try{
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper=new ObjectMapper();
            return mapper.readValue(o.toString(), entityClass);
        }
        catch(Exception e){
            log.error("Exception",e);
            return null;
        }
    }
    public void  set(String key, Object o,long ttl){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String jsonValue = mapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key,jsonValue,ttl,TimeUnit.MINUTES);
        }
        catch(Exception e){
            log.error("Exception",e);
        }
    }
}
