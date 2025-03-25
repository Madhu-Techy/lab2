package com.example.courseapp.dto;

import lombok.Data;

@Data
public class EnrollmentDto {
    private String userId;
    private String courseId;
    private double progress;
    private boolean completed;
}