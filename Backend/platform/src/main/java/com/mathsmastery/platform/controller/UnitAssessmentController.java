package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.UnitAssessmentResponse;
import com.mathsmastery.platform.service.AssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/units")
@CrossOrigin
public class UnitAssessmentController {

    private final AssessmentService assessmentService;

    public UnitAssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }
    @GetMapping("/{unitId}/assessments")
    public ResponseEntity<UnitAssessmentResponse> getUnitAssessments(
            @PathVariable Integer unitId
    ) {
        return ResponseEntity.ok(
                assessmentService.getAssessmentsByUnit(unitId)
        );
    }
    @GetMapping("/{unitId}/assessments/{assessmentId}")
    public ResponseEntity<?> getAssessmentDetails(
            @PathVariable Integer unitId,
            @PathVariable Integer assessmentId
    ) {
        return ResponseEntity.ok(
                assessmentService.getAssessmentDetails(unitId, assessmentId)
        );
    }
    @GetMapping("/{unitId}/assessments/teacher-view")
    public ResponseEntity<?> getTeacherAssessments(
            @PathVariable Integer unitId,
            @RequestParam(required = false) String status
    ) {
        return ResponseEntity.ok(
                assessmentService.getTeacherAssessments(unitId, status)
        );
    }
    @GetMapping("/{unitId}/assessments/upcoming")
    public ResponseEntity<?> getUpcomingAssessments(
            @PathVariable Integer unitId
    ) {
        return ResponseEntity.ok(
                assessmentService.getUpcomingAssessments(unitId)
        );
    }
}