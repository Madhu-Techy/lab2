package com.example.courseapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "templates")
@Data
public class Template {
    @Id
    private String id;
    private String name;
    private List<String> defaultSteps; // Default steps for courses using this template
    private String category;
}