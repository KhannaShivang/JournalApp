package com.khanna.journelApp.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.khanna.journelApp.cashe.AppCashe;

@Component
public class AppCacheScheduler {
    
    @Autowired
    AppCashe appCashe;

    @Scheduled(cron = "0 0/30 * 1/1 * *")
    public void refreshAppCache(){
        appCashe.init();
    }
}
