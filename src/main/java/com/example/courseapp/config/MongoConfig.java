package com.example.courseapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {
    
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb+srv://skillshare:courseapp@cluster0.llf5a.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");
    }
    
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "courseapp");
    }
}