package com.example.courseapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "courses")
@Data
public class Course {
    @Id
    private String id;
    private String title;
    private String description;
    private String instructorId;
    private List<String> templateSteps; // Steps from the template
    private boolean published = false;
    private String category;
    private String imageUrl;
}