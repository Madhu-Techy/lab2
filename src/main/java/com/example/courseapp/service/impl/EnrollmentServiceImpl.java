package com.example.courseapp.service.impl;

import com.example.courseapp.exception.CustomException;
import com.example.courseapp.model.Course;
import com.example.courseapp.model.Enrollment;
import com.example.courseapp.model.User;
import com.example.courseapp.repository.CourseRepository;
import com.example.courseapp.repository.EnrollmentRepository;
import com.example.courseapp.repository.UserRepository;
import com.example.courseapp.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public Enrollment enrollUser(String userId, String courseId) {
        // Fetch user since we need user data
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found with id: " + userId));
        
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CustomException("Course not found with id: " + courseId));
        
        if (!course.isPublished()) {
            throw new CustomException("Cannot enroll in unpublished course");
        }
        
        if (enrollmentRepository.existsByUserIdAndCourseId(userId, courseId)) {
            throw new CustomException("User is already enrolled in this course");
        }
    
        Enrollment enrollment = new Enrollment();
        enrollment.setUserId(userId);
        enrollment.setCourseId(courseId);
        
        // ACTUALLY USING THE USER DATA
        enrollment.setUserEmail(user.getEmail()); // Store user email in enrollment
        enrollment.setUserName(user.getUsername()); // Store username
        enrollment.setInstructor(user.isInstructor()); // Store if user is instructor
        
        // Initialize all course steps as incomplete
        Map<String, Boolean> completedSteps = new HashMap<>();
        course.getTemplateSteps().forEach(step -> completedSteps.put(step, false));
        enrollment.setCompletedSteps(completedSteps);
        enrollment.setProgress(0.0);
        enrollment.setCompleted(false);
        
        // Log enrollment with user info
        log.info("User {} (email: {}) enrolled in course {}", 
                user.getUsername(), 
                user.getEmail(), 
                course.getTitle());
        
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment getEnrollment(String enrollmentId) {
        return enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new CustomException("Enrollment not found with id: " + enrollmentId));
    }

    @Override
    public List<Enrollment> getUserEnrollments(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException("User not found with id: " + userId);
        }
        return enrollmentRepository.findByUserId(userId);
    }

    @Override
    public List<Enrollment> getCourseEnrollments(String courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new CustomException("Course not found with id: " + courseId);
        }
        return enrollmentRepository.findByCourseId(courseId);
    }

    @Override
    @Transactional
    public Enrollment updateProgress(String enrollmentId, String stepId, boolean completed) {
        Enrollment enrollment = getEnrollment(enrollmentId);
        
        if (!enrollment.getCompletedSteps().containsKey(stepId)) {
            throw new CustomException("Invalid step ID: " + stepId);
        }
        
        // Update the completion status of the specific step
        enrollment.getCompletedSteps().put(stepId, completed);
        
        // Recalculate overall progress
        double newProgress = calculateProgress(enrollmentId);
        enrollment.setProgress(newProgress);
        
        // Mark as completed if progress is 100%
        if (newProgress == 1.0) {
            enrollment.setCompleted(true);
        }
        
        return enrollmentRepository.save(enrollment);
    }

    @Override
    @Transactional
    public void unenroll(String enrollmentId) {
        if (!enrollmentRepository.existsById(enrollmentId)) {
            throw new CustomException("Enrollment not found with id: " + enrollmentId);
        }
        enrollmentRepository.deleteById(enrollmentId);
    }

    @Override
    public double calculateProgress(String enrollmentId) {
        Enrollment enrollment = getEnrollment(enrollmentId);
        
        if (enrollment.getCompletedSteps().isEmpty()) {
            return 0.0;
        }
        
        long completedCount = enrollment.getCompletedSteps().values()
                .stream()
                .filter(Boolean::booleanValue)
                .count();
        
        return (double) completedCount / enrollment.getCompletedSteps().size();
    }
}