package com.khanna.journelApp.repositry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class MongoTempletImplTests {
    
    @Autowired
    private MongoTemplateImpl mongoTemplateImpl;

    @Test
    public void getUserForSATest(){
        Assert.isTrue(mongoTemplateImpl.getUserForSA().size()>0,"No User");
    }
}
