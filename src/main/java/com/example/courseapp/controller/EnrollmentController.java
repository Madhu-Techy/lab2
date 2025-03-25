package com.example.courseapp.controller;

import com.example.courseapp.model.Enrollment;
import com.example.courseapp.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enroll/{courseId}")
    public ResponseEntity<Enrollment> enroll(@PathVariable String courseId,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(enrollmentService.enrollUser(userDetails.getUsername(), courseId));
    }

    @PutMapping("/{enrollmentId}/progress")
    public ResponseEntity<Enrollment> updateProgress(@PathVariable String enrollmentId,
                                                   @RequestParam String stepId,
                                                   @RequestParam boolean completed) {
        return ResponseEntity.ok(enrollmentService.updateProgress(enrollmentId, stepId, completed));
    }

    @GetMapping("/user")
    public ResponseEntity<List<Enrollment>> getUserEnrollments(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(enrollmentService.getUserEnrollments(userDetails.getUsername()));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getCourseEnrollments(@PathVariable String courseId) {
        return ResponseEntity.ok(enrollmentService.getCourseEnrollments(courseId));
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<Void> unenroll(@PathVariable String enrollmentId) {
        enrollmentService.unenroll(enrollmentId);
        return ResponseEntity.noContent().build();
    }
}