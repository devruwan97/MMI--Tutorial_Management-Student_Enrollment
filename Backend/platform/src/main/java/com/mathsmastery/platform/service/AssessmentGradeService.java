package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.AssessmentGradeDTO;
import com.mathsmastery.platform.model.Assessment;
import com.mathsmastery.platform.model.AssessmentGrade;
import com.mathsmastery.platform.model.Student;
import com.mathsmastery.platform.model.Submission;
import com.mathsmastery.platform.repository.AssessmentGradeRepository;
import com.mathsmastery.platform.repository.AssessmentRepository;
import com.mathsmastery.platform.repository.StudentRepository;
import com.mathsmastery.platform.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentGradeService {

    private final AssessmentGradeRepository gradeRepository;
    private final AssessmentRepository assessmentRepository;
    private final StudentRepository studentRepository;
    private final SubmissionRepository submissionRepository;

    public AssessmentGradeService(
            AssessmentGradeRepository gradeRepository,
            AssessmentRepository assessmentRepository,
            StudentRepository studentRepository,
            SubmissionRepository submissionRepository
    ) {
        this.gradeRepository = gradeRepository;
        this.assessmentRepository = assessmentRepository;
        this.studentRepository = studentRepository;
        this.submissionRepository = submissionRepository;
    }

    public List<AssessmentGradeDTO> getGradesByAssessment(Integer assessmentId) {

        List<AssessmentGrade> grades =
                gradeRepository.findByAssessment_Id(assessmentId);

        return grades.stream()
                .map(this::mapToDTO)
                .toList();
    }

    public AssessmentGradeDTO getStudentGrade(Integer assessmentId, Integer studentId) {

        AssessmentGrade grade = gradeRepository
                .findByAssessment_IdAndStudent_Id(assessmentId, studentId)
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        return mapToDTO(grade);
    }

    public AssessmentGradeDTO createGrade(AssessmentGradeDTO dto) {

        Assessment assessment = assessmentRepository.findById(dto.getAssessmentId())
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        Student student = studentRepository.findById(Long.valueOf(dto.getStudentId()))
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (gradeRepository.existsByAssessment_IdAndStudent_Id(
                assessment.getId(),
                student.getId().intValue()
        )) {
            throw new RuntimeException("Grade already exists. Use update.");
        }

        AssessmentGrade grade = new AssessmentGrade();
        grade.setAssessment(assessment);
        grade.setStudent(student);
        grade.setMarks(dto.getMarks());
        grade.setGrade(calculateGrade(dto.getMarks()));

        AssessmentGrade saved = gradeRepository.save(grade);

        submissionRepository
                .findByAssessmentIdAndStudentId(
                        assessment.getId(),
                        student.getId().intValue()
                )
                .ifPresent(sub -> {
                    sub.setStatus(Submission.Status.GRADED);
                    submissionRepository.save(sub);
                });

        return mapToDTO(saved);
    }

    public AssessmentGradeDTO updateGrade(Integer gradeId, AssessmentGradeDTO dto) {

        AssessmentGrade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        if (dto.getMarks() != null) {
            grade.setMarks(dto.getMarks());
            grade.setGrade(calculateGrade(dto.getMarks()));
        }

        AssessmentGrade saved = gradeRepository.save(grade);

        submissionRepository
                .findByAssessmentIdAndStudentId(
                        grade.getAssessment().getId(),
                        grade.getStudent().getId().intValue()
                )
                .ifPresent(sub -> {
                    sub.setStatus(Submission.Status.GRADED);
                    submissionRepository.save(sub);
                });

        return mapToDTO(saved);
    }

    public void deleteGrade(Integer gradeId) {

        AssessmentGrade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        submissionRepository
                .findByAssessmentIdAndStudentId(
                        grade.getAssessment().getId(),
                        grade.getStudent().getId().intValue()
                )
                .ifPresent(sub -> {
                    sub.setStatus(Submission.Status.PENDING);
                    submissionRepository.save(sub);
                });

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

    private AssessmentGradeDTO mapToDTO(AssessmentGrade g) {

        AssessmentGradeDTO dto = new AssessmentGradeDTO();

        dto.setId(g.getId());
        dto.setAssessmentId(g.getAssessment().getId());
        dto.setStudentId(g.getStudent().getId());
        dto.setMarks(g.getMarks());
        dto.setGrade(g.getGrade());

        return dto;
    }
}