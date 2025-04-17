package com.khanna.journelApp.repositry;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.khanna.journelApp.entity.User;

public interface UserRepo extends MongoRepository<User,ObjectId>{

    public User findByUserName(String userName);

    public void deleteByUserName(String userName);
}
