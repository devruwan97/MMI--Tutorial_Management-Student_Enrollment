package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.EnrollmentRequest;
import com.mathsmastery.platform.model.Enrollment;
import com.mathsmastery.platform.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@Tag(name = "Enrollment API", description = "Manage student enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Operation(summary = "Get all enrollments")
    @GetMapping
    public List<Enrollment> getAll() {
        return enrollmentService.getAllEnrollments();
    }

    @Operation(summary = "Enroll a student into a course")
    @PostMapping
    public Enrollment create(@RequestBody EnrollmentRequest request) {
        return enrollmentService.createEnrollment(request);
    }

    @Operation(summary = "Get enrollments by student ID")
    @GetMapping("/student/{studentId}")
    public List<Enrollment> getByStudent(@PathVariable Integer studentId) {
        return enrollmentService.getByStudent(studentId);
    }

    @Operation(summary = "Get enrollments by course ID")
    @GetMapping("/course/{courseId}")
    public List<Enrollment> getByCourse(@PathVariable Integer courseId) {
        return enrollmentService.getByCourse(courseId);
    }

    @Operation(summary = "Update enrollment status")
    @PutMapping("/{id}")
    public Enrollment updateStatus(
            @PathVariable Integer id,
            @RequestParam String status
    ) {
        return enrollmentService.updateStatus(id, status);
    }

    @Operation(summary = "Delete enrollment")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        enrollmentService.delete(id);
    }
}