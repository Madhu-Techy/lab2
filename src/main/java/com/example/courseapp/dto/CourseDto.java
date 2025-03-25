package com.example.courseapp.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class CourseDto {
    @NotBlank
    private String title;
    
    private String description;
    
    @NotBlank
    private String templateId;
    
    private String category;
    
    private String imageUrl;
}