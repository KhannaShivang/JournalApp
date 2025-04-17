package com.khanna.journelApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    EmailService emailService;
    @Test
    void sendMailTest(){
        emailService.sendmail("shivangkhanna140903@gmail.com", "Testing java Mail Service", "Hope it worked");
    }
}
