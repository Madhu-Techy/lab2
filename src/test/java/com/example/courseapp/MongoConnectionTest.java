package com.example.courseapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MongoConnectionTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongoConnection() {
        // Attempt to list collections to verify the connection
        var collections = mongoTemplate.getDb().listCollectionNames();
        assertNotNull(collections, "Collections should not be null");
        System.out.println("Connected to MongoDB. Collections: ");
        collections.forEach(System.out::println);
    }
}
