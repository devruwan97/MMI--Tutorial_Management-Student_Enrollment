package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.AssessmentGradeDTO;
import com.mathsmastery.platform.service.AssessmentGradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessment-grades")
@CrossOrigin
public class AssessmentGradeController {

    private final AssessmentGradeService assessmentGradeService;

    public AssessmentGradeController(AssessmentGradeService assessmentGradeService) {
        this.assessmentGradeService = assessmentGradeService;
    }

    @GetMapping("/assessment/{assessmentId}")
    public ResponseEntity<List<AssessmentGradeDTO>> getGradesByAssessment(
            @PathVariable Integer assessmentId
    ) {
        return ResponseEntity.ok(
                assessmentGradeService.getGradesByAssessment(assessmentId)
        );
    }

    @GetMapping("/assessment/{assessmentId}/student/{studentId}")
    public ResponseEntity<AssessmentGradeDTO> getStudentGrade(
            @PathVariable Integer assessmentId,
            @PathVariable Integer studentId
    ) {
        return ResponseEntity.ok(
                assessmentGradeService.getStudentGrade(assessmentId, studentId)
        );
    }

    @PostMapping
    public ResponseEntity<AssessmentGradeDTO> createGrade(
            @RequestBody AssessmentGradeDTO dto
    ) {
        return ResponseEntity.ok(
                assessmentGradeService.createGrade(dto)
        );
    }

    @PutMapping("/{gradeId}")
    public ResponseEntity<AssessmentGradeDTO> updateGrade(
            @PathVariable Integer gradeId,
            @RequestBody AssessmentGradeDTO dto
    ) {
        return ResponseEntity.ok(
                assessmentGradeService.updateGrade(gradeId, dto)
        );
    }

    @DeleteMapping("/{gradeId}")
    public ResponseEntity<String> deleteGrade(
            @PathVariable Integer gradeId
    ) {
        assessmentGradeService.deleteGrade(gradeId);
        return ResponseEntity.ok("Grade deleted successfully");
    }
}