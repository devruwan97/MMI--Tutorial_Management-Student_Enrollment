package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.AssessmentGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssessmentGradeRepository extends JpaRepository<AssessmentGrade, Integer> {

    List<AssessmentGrade> findByAssessmentId(Integer assessmentId);

    List<AssessmentGrade> findByStudentId(Integer studentId);

    Optional<AssessmentGrade> findByAssessmentIdAndStudentId(
            Integer assessmentId,
            Integer studentId
    );

    boolean existsByAssessmentIdAndStudentId(
            Integer assessmentId,
            Integer studentId
    );
}