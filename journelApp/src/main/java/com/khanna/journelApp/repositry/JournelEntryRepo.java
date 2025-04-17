package com.khanna.journelApp.repositry;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.khanna.journelApp.entity.JournelEntry;

public interface JournelEntryRepo extends MongoRepository<JournelEntry,ObjectId>{

}
