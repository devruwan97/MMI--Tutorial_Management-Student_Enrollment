package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.AssessmentGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssessmentGradeRepository extends JpaRepository<AssessmentGrade, Integer> {

    List<AssessmentGrade> findByAssessment_Id(Integer assessmentId);

    List<AssessmentGrade> findByStudent_Id(Integer studentId);

    Optional<AssessmentGrade> findByAssessment_IdAndStudent_Id(
            Integer assessmentId,
            Integer studentId
    );

    boolean existsByAssessment_IdAndStudent_Id(
            Integer assessmentId,
            Integer studentId
    );
}