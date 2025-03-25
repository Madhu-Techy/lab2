package com.example.courseapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;

@Document(collection = "enrollments")
@Data
public class Enrollment {
    @Id
    private String id;
    private String userId;
    private String courseId;
    private Map<String, Boolean> completedSteps; // StepId -> completion status
    private double progress = 0.0;
    private boolean completed = false;
    private String userEmail;
    private String userName;
    private boolean isInstructor;
    
}