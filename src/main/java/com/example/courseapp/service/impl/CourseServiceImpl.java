package com.example.courseapp.service.impl;

import com.example.courseapp.dto.CourseDto;
import com.example.courseapp.exception.CustomException;
import com.example.courseapp.model.Course;
import com.example.courseapp.model.Template;
import com.example.courseapp.model.User;
import com.example.courseapp.repository.CourseRepository;
import com.example.courseapp.repository.TemplateRepository;
import com.example.courseapp.repository.UserRepository;
import com.example.courseapp.service.CourseService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TemplateRepository templateRepository;
    private final UserRepository userRepository;

    public CourseServiceImpl(CourseRepository courseRepository, 
                            TemplateRepository templateRepository,
                            UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.templateRepository = templateRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Course createCourse(CourseDto courseDto, String instructorId) {
        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new CustomException("Instructor not found"));
        
        if (!instructor.isInstructor()) {
            throw new CustomException("Only instructors can create courses");
        }
        
        Template template = templateRepository.findById(courseDto.getTemplateId())
                .orElseThrow(() -> new CustomException("Template not found"));
        
        Course course = new Course();
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setInstructorId(instructorId);
        course.setTemplateSteps(template.getDefaultSteps());
        course.setCategory(courseDto.getCategory());
        course.setImageUrl(courseDto.getImageUrl());
        
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CustomException("Course not found"));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCoursesByInstructor(String instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    @Override
    public Course updateCourse(String id, CourseDto courseDto) {
        Course course = getCourseById(id);
        if (courseDto.getTitle() != null) course.setTitle(courseDto.getTitle());
        if (courseDto.getDescription() != null) course.setDescription(courseDto.getDescription());
        if (courseDto.getCategory() != null) course.setCategory(courseDto.getCategory());
        if (courseDto.getImageUrl() != null) course.setImageUrl(courseDto.getImageUrl());
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course publishCourse(String courseId) {
        Course course = getCourseById(courseId);
        course.setPublished(true);
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getPublishedCourses() {
        return courseRepository.findByPublished(true);
    }

    @Override
    public List<Course> getCoursesByCategory(String category) {
        return courseRepository.findByCategoryAndPublished(category, true);
    }
}