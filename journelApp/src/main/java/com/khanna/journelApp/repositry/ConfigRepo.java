package com.khanna.journelApp.repositry;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.khanna.journelApp.entity.ConfigEntity;

public interface ConfigRepo extends MongoRepository<ConfigEntity,ObjectId>{

}
