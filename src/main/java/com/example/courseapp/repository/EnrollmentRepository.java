package com.example.courseapp.repository;

import com.example.courseapp.model.Enrollment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends MongoRepository<Enrollment, String> {
    List<Enrollment> findByUserId(String userId);
    List<Enrollment> findByCourseId(String courseId);
    Optional<Enrollment> findByUserIdAndCourseId(String userId, String courseId);
    boolean existsByUserIdAndCourseId(String userId, String courseId);
}