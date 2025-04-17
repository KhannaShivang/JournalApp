package com.khanna.journelApp.cashe;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khanna.journelApp.entity.ConfigEntity;
import com.khanna.journelApp.repositry.ConfigRepo;

import jakarta.annotation.PostConstruct;



@Component
public class AppCashe {
    public HashMap<String,String>cache;

    @Autowired
    private ConfigRepo configRepo;
    @PostConstruct
    public void init(){
        cache=new HashMap<>();
        List<ConfigEntity> configs = configRepo.findAll();
        for(ConfigEntity entity:configs){
            cache.put(entity.getKey(),entity.getValue());
        }
    }    
}
