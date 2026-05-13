package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.SubmissionDTO;
import com.mathsmastery.platform.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@CrossOrigin
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping("/assessment/{assessmentId}")
    public ResponseEntity<List<SubmissionDTO>> getByAssessment(
            @PathVariable Integer assessmentId
    ) {
        return ResponseEntity.ok(
                submissionService.getByAssessment(assessmentId)
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SubmissionDTO>> getByStudent(
            @PathVariable Integer studentId
    ) {
        return ResponseEntity.ok(
                submissionService.getByStudent(studentId)
        );
    }

    @GetMapping("/assessment/{assessmentId}/pending")
    public ResponseEntity<List<SubmissionDTO>> getPendingSubmissions(
            @PathVariable Integer assessmentId
    ) {
        return ResponseEntity.ok(
                submissionService.getPendingByAssessment(assessmentId)
        );
    }
}