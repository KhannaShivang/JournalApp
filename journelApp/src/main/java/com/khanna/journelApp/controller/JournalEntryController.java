package com.khanna.journelApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khanna.journelApp.entity.JournelEntry;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    private HashMap<ObjectId,JournelEntry> journelEntries=new HashMap<>();

    @GetMapping
    public List<JournelEntry> getAll(){ //localhost:8080/journel  GET
        return new ArrayList<>(journelEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournelEntry myEntry){  //localhost:8080/journel  POST
        journelEntries.put(myEntry.getId(),myEntry);
        return true;
    }

    @GetMapping("id/{id}")
    public JournelEntry getEntryById(@PathVariable ObjectId id){
        return journelEntries.get(id);
    }

    @DeleteMapping("id/{id}")
    public JournelEntry deleteEntryById(@PathVariable ObjectId id){
        return journelEntries.remove(id);
    }

    @PutMapping("id/{id}")
    public JournelEntry updateEntryById(@PathVariable ObjectId id,@RequestBody JournelEntry myEntry){
        return journelEntries.put(id,myEntry);
    }
}
