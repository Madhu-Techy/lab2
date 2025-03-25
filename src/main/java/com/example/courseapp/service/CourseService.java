package com.example.courseapp.service;

import com.example.courseapp.dto.CourseDto;
import com.example.courseapp.model.Course;
import java.util.List;

public interface CourseService {
    Course createCourse(CourseDto courseDto, String instructorId);
    Course getCourseById(String id);
    List<Course> getAllCourses();
    List<Course> getCoursesByInstructor(String instructorId);
    Course updateCourse(String id, CourseDto courseDto);
    void deleteCourse(String id);
    Course publishCourse(String courseId);
    List<Course> getPublishedCourses();
    List<Course> getCoursesByCategory(String category);
}