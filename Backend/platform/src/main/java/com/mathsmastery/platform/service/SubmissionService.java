package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.SubmissionDTO;
import com.mathsmastery.platform.model.Assessment;
import com.mathsmastery.platform.model.Student;
import com.mathsmastery.platform.model.Submission;
import com.mathsmastery.platform.repository.AssessmentRepository;
import com.mathsmastery.platform.repository.StudentRepository;
import com.mathsmastery.platform.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final StudentRepository studentRepository;
    private final AssessmentRepository assessmentRepository;

    public SubmissionDTO submitAssessment(SubmissionDTO dto) {

        Student student = studentRepository.findByUserId(Long.valueOf(dto.getStudentId()))
                .orElseThrow(() ->
                        new RuntimeException("Student not found for userId: " + dto.getStudentId())
                );

        Assessment assessment = assessmentRepository.findById(dto.getAssessmentId())
                .orElseThrow(() ->
                        new RuntimeException("Assessment not found: " + dto.getAssessmentId())
                );

        Submission submission = new Submission();
        submission.setStudent(student);
        submission.setAssessment(assessment);
        submission.setFileName(dto.getFileName());
        submission.setFileUrl(dto.getFileUrl());
        submission.setSubmittedAt(LocalDateTime.now());
        submission.setStatus(Submission.Status.PENDING);

        Submission saved = submissionRepository.save(submission);
        dto.setId(saved.getId());
        dto.setSubmittedAt(saved.getSubmittedAt());
        dto.setStatus(saved.getStatus().name());

        return dto;
    }

    public List<SubmissionDTO> getByAssessment(Integer assessmentId) {

        List<Submission> submissions =
                submissionRepository.findByAssessmentId(assessmentId);

        List<SubmissionDTO> result = new ArrayList<>();

        for (Submission s : submissions) {

            SubmissionDTO dto = new SubmissionDTO();

            dto.setId(s.getId());
            dto.setAssessmentId(s.getAssessment().getId());
            dto.setStudentId(s.getStudent().getId());
            dto.setStudentName(s.getStudent().getUser().getName());
            dto.setFileName(s.getFileName());
            dto.setFileUrl(s.getFileUrl());
            dto.setSubmittedAt(s.getSubmittedAt());
            dto.setStatus(s.getStatus() != null ? s.getStatus().name() : "PENDING");

            result.add(dto);
        }

        return result;
    }

    public List<SubmissionDTO> getByStudent(Integer studentId) {

        List<Submission> submissions =
                submissionRepository.findByStudentId(studentId);

        List<SubmissionDTO> result = new ArrayList<>();

        for (Submission s : submissions) {

            SubmissionDTO dto = new SubmissionDTO();

            dto.setId(s.getId());
            dto.setAssessmentId(s.getAssessment().getId());
            dto.setStudentId(s.getStudent().getId());
            dto.setStudentName(s.getStudent().getUser().getName());
            dto.setFileName(s.getFileName());
            dto.setFileUrl(s.getFileUrl());
            dto.setSubmittedAt(s.getSubmittedAt());
            dto.setStatus(s.getStatus() != null ? s.getStatus().name() : "PENDING");

            result.add(dto);
        }

        return result;
    }

    public List<SubmissionDTO> getPendingByAssessment(Integer assessmentId) {

        List<Submission> submissions =
                submissionRepository.findByAssessmentIdAndStatus(
                        assessmentId,
                        Submission.Status.PENDING
                );

        List<SubmissionDTO> result = new ArrayList<>();

        for (Submission s : submissions) {

            SubmissionDTO dto = new SubmissionDTO();

            dto.setId(s.getId());
            dto.setAssessmentId(s.getAssessment().getId());
            dto.setStudentId(s.getStudent().getId());
            dto.setStudentName(s.getStudent().getUser().getName());
            dto.setFileName(s.getFileName());
            dto.setFileUrl(s.getFileUrl());
            dto.setSubmittedAt(s.getSubmittedAt());
            dto.setStatus("PENDING");

            result.add(dto);
        }

        return result;
    }

    public List<SubmissionDTO> getGradedByAssessment(Integer assessmentId) {

        List<Submission> submissions =
                submissionRepository.findByAssessmentIdAndStatus(
                        assessmentId,
                        Submission.Status.GRADED
                );

        List<SubmissionDTO> result = new ArrayList<>();

        for (Submission s : submissions) {

            SubmissionDTO dto = new SubmissionDTO();

            dto.setId(s.getId());
            dto.setAssessmentId(s.getAssessment().getId());
            dto.setStudentId(s.getStudent().getId());
            dto.setStudentName(s.getStudent().getUser().getName());
            dto.setFileName(s.getFileName());
            dto.setFileUrl(s.getFileUrl());
            dto.setSubmittedAt(s.getSubmittedAt());
            dto.setStatus("GRADED");

            result.add(dto);
        }

        return result;
    }
}