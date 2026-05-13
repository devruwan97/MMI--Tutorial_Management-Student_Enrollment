package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

    List<Submission> findByAssessmentId(Integer assessmentId);

    List<Submission> findByStudentId(Integer studentId);

    List<Submission> findByAssessmentIdAndStatus(Integer assessmentId, String status);
}