package com.khanna.journelApp.Scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.khanna.journelApp.entity.JournelEntry;
import com.khanna.journelApp.entity.Sentiment;
import com.khanna.journelApp.entity.User;
import com.khanna.journelApp.repositry.MongoTemplateImpl;
import com.khanna.journelApp.service.EmailService;

@Component
public class EmailScheduler {
    
    @Autowired
    EmailService emailService;
    @Autowired
    MongoTemplateImpl mongoTemplateImpl;

    @Scheduled(cron = "0 0 10 ? * SUN")
    public void fetchUsersSendSAMail(){
        List<User> users = mongoTemplateImpl.getUserForSA();
        for(User user:users){
            List<JournelEntry>entries = user.getJournalEntries();
            List<Sentiment> sentiments = entries.stream().filter(u->u.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment()).collect(Collectors.toList());
            HashMap<Sentiment,Integer>map = new HashMap<>();
            for(Sentiment s:sentiments){
                if(s!=null)
                    map.put(s,map.getOrDefault(s,0)+1);
            }
            int max=0;
            Sentiment majorSentiment = null;
            for(Sentiment s : map.keySet()){
                if(map.get(s) > max){
                    max = map.get(s);
                    majorSentiment = s;
                }
            }
            if(majorSentiment != null){
                emailService.sendmail(user.getEmail(), "Your Sentiment For Past Seven Days", majorSentiment.toString());
            }
        }
    }
}
