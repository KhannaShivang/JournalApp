package com.khanna.journelApp.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khanna.journelApp.entity.JournelEntry;
import com.khanna.journelApp.entity.User;
import com.khanna.journelApp.service.JournalEntryService;
import com.khanna.journelApp.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournelEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllForUser(){ 
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userService.findByUserName(userName);
        if(user==null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<JournelEntry>journelEntries=user.getJournalEntries();
        if(journelEntries!=null&&!journelEntries.isEmpty()){
            return new ResponseEntity<>(journelEntries,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<?> createEntry(@RequestBody JournelEntry myEntry){  //localhost:8080/journel  POST
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        journalEntryService.saveEntry(myEntry,userName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournelEntry> getEntryById(@PathVariable ObjectId id){
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userService.findByUserName(userName);
        List<JournelEntry>list = user.getJournalEntries().stream().filter(i -> i.getId().equals(id)).collect(Collectors.toList());
        if(!list.isEmpty()){
            Optional<JournelEntry>journalEntry=journalEntryService.findById(id);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId id){
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        boolean removed = journalEntryService.deleteById(id,userName);
        if(removed) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournelEntry> updateEntryById(@PathVariable ObjectId id,@RequestBody JournelEntry myEntry){
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userService.findByUserName(userName);
        List<JournelEntry>list = user.getJournalEntries().stream().filter(i -> i.getId().equals(id)).collect(Collectors.toList());
        if(!list.isEmpty()){
            Optional<JournelEntry>journalEntry=journalEntryService.findById(id);
            if(journalEntry.isPresent()){
                JournelEntry prevEntry = journalEntry.get();
                prevEntry.setTitle(myEntry.getTitle()!=null&&!myEntry.getTitle().equals("")?myEntry.getTitle():prevEntry.getTitle());
                prevEntry.setContent(myEntry.getContent()!=null&&!myEntry.getContent().equals("")?myEntry.getContent():prevEntry.getContent());
                journalEntryService.saveEntry(prevEntry);
                return new ResponseEntity<>(prevEntry,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
