package com.khanna.journelApp.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournelEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    
    private Sentiment sentiment;
}
