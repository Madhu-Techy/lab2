package com.example.courseapp.service;

import com.example.courseapp.model.Enrollment;
import java.util.List;

public interface EnrollmentService {
    Enrollment enrollUser(String userId, String courseId);
    Enrollment getEnrollment(String enrollmentId);
    List<Enrollment> getUserEnrollments(String userId);
    List<Enrollment> getCourseEnrollments(String courseId);
    Enrollment updateProgress(String enrollmentId, String stepId, boolean completed);
    void unenroll(String enrollmentId);
    double calculateProgress(String enrollmentId);  // This is the method that needs implementation
}