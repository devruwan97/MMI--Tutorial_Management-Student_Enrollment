package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.AssessmentGradeDTO;
import com.mathsmastery.platform.model.Assessment;
import com.mathsmastery.platform.model.AssessmentGrade;
import com.mathsmastery.platform.model.Student;
import com.mathsmastery.platform.repository.AssessmentGradeRepository;
import com.mathsmastery.platform.repository.AssessmentRepository;
import com.mathsmastery.platform.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssessmentGradeService {

    private final AssessmentGradeRepository gradeRepository;
    private final AssessmentRepository assessmentRepository;
    private final StudentRepository studentRepository;

    public AssessmentGradeService(AssessmentGradeRepository gradeRepository, AssessmentRepository assessmentRepository, StudentRepository studentRepository) {
        this.gradeRepository = gradeRepository;
        this.assessmentRepository = assessmentRepository;
        this.studentRepository = studentRepository;
    }

    public List<AssessmentGradeDTO> getGradesByAssessment(Integer assessmentId) {

        List<AssessmentGrade> grades =
                gradeRepository.findByAssessmentId(assessmentId);

        List<AssessmentGradeDTO> result = new ArrayList<>();

        for (AssessmentGrade g : grades) {
            AssessmentGradeDTO dto = new AssessmentGradeDTO();
            dto.setId(g.getId());
            dto.setAssessmentId(g.getAssessment().getId());
            dto.setStudentId(g.getStudent().getId());
            dto.setMarks(g.getMarks());
            dto.setGrade(g.getGrade());
            result.add(dto);
        }

        return result;
    }

    public AssessmentGradeDTO getStudentGrade(Integer assessmentId, Integer studentId) {

        Optional<AssessmentGrade> optional =
                gradeRepository.findByAssessmentIdAndStudentId(assessmentId, studentId);

        if (optional.isEmpty()) {
            throw new RuntimeException("Grade not found");
        }

        AssessmentGrade g = optional.get();

        AssessmentGradeDTO dto = new AssessmentGradeDTO();
        dto.setId(g.getId());
        dto.setAssessmentId(g.getAssessment().getId());
        dto.setStudentId(g.getStudent().getId());
        dto.setMarks(g.getMarks());
        dto.setGrade(g.getGrade());

        return dto;
    }

    public AssessmentGradeDTO createGrade(AssessmentGradeDTO dto) {

        Assessment assessment = assessmentRepository.findById(dto.getAssessmentId())
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        Student student = studentRepository.findById(Long.valueOf(dto.getStudentId()))
                .orElseThrow(() -> new RuntimeException("Student not found"));

        AssessmentGrade grade = new AssessmentGrade();

        grade.setAssessment(assessment);
        grade.setStudent(student);
        grade.setMarks(dto.getMarks());
        grade.setGrade(calculateGrade(dto.getMarks()));

        AssessmentGrade saved = gradeRepository.save(grade);

        AssessmentGradeDTO response = new AssessmentGradeDTO();
        response.setId(saved.getId());
        response.setAssessmentId(assessment.getId());
        response.setStudentId(student.getId());
        response.setMarks(saved.getMarks());
        response.setGrade(saved.getGrade());

        return response;
    }

    public AssessmentGradeDTO updateGrade(Integer gradeId, AssessmentGradeDTO dto) {

        Optional<AssessmentGrade> optional =
                gradeRepository.findById(gradeId);

        if (optional.isEmpty()) {
            throw new RuntimeException("Grade not found");
        }

        AssessmentGrade grade = optional.get();

        grade.setMarks(dto.getMarks());

        grade.setGrade(calculateGrade(dto.getMarks()));

        AssessmentGrade updated = gradeRepository.save(grade);

        AssessmentGradeDTO response = new AssessmentGradeDTO();
        response.setId(updated.getId());
        response.setAssessmentId(updated.getAssessment().getId());
        response.setStudentId(updated.getStudent().getId());
        response.setMarks(updated.getMarks());
        response.setGrade(updated.getGrade());

        return response;
    }
    public void deleteGrade(Integer gradeId) {

        if (!gradeRepository.existsById(gradeId)) {
            throw new RuntimeException("Grade not found");
        }

        gradeRepository.deleteById(gradeId);
    }

    private String calculateGrade(Double marks) {

        if (marks == null) return "N/A";

        if (marks >= 85) return "A";
        if (marks >= 70) return "B";
        if (marks >= 55) return "C";
        if (marks >= 40) return "D";

        return "F";
    }
}