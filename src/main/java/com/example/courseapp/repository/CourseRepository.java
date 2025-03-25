package com.example.courseapp.repository;

import com.example.courseapp.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> findByInstructorId(String instructorId);
    List<Course> findByPublished(boolean published);
    List<Course> findByCategoryAndPublished(String category, boolean published);
}