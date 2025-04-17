package com.khanna.journelApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khanna.journelApp.entity.JournelEntry;
import com.khanna.journelApp.entity.User;
import com.khanna.journelApp.repositry.JournelEntryRepo;

@Service
public class JournalEntryService {
    
    @Autowired
    private JournelEntryRepo journalEntryrepo;
    @Autowired
    private UserService userService; 

    @Transactional
    public void saveEntry(JournelEntry journalEntry,String userName){
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        user.getJournalEntries().add(journalEntry);
        journalEntryrepo.save(journalEntry);
        user.setUserName(userName);
        userService.saveUser(user);
    }
    
    public void saveEntry(JournelEntry journelEntry){
        journalEntryrepo.save(journelEntry);
    }

    public List<JournelEntry> getAll(){
        return journalEntryrepo.findAll();
    }
    public Optional<JournelEntry> findById(ObjectId id){
        return journalEntryrepo.findById(id);
    }
    public boolean deleteById(ObjectId id,String userName){
        User user = userService.findByUserName(userName);
        boolean removed = user.getJournalEntries().removeIf(x->x.getId().equals(id));
        if(removed){
            userService.saveUser(user);
            journalEntryrepo.deleteById(id);
        }
        return removed;
    }
}
