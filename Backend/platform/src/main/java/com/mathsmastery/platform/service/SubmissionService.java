package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.SubmissionDTO;
import com.mathsmastery.platform.model.Submission;
import com.mathsmastery.platform.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
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

            result.add(dto);
        }

        return result;
    }

    public List<SubmissionDTO> getPendingByAssessment(Integer assessmentId) {

        List<Submission> submissions =
                submissionRepository.findByAssessmentIdAndStatus(assessmentId, "PENDING");

        List<SubmissionDTO> result = new ArrayList<>();

        for (Submission s : submissions) {
            SubmissionDTO dto = new SubmissionDTO();
            dto.setId(s.getId());
            dto.setAssessmentId(s.getAssessment().getId());
            dto.setStudentId(s.getStudent().getId());
            dto.setFileUrl(s.getFileUrl());
            dto.setStatus(s.getStatus());
            dto.setSubmittedAt(s.getSubmittedAt());
            result.add(dto);
        }

        return result;
    }
}