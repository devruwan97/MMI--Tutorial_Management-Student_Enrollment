package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {
    List<Assessment> findByUnitId(Integer unitId);
    Optional<Assessment> findByIdAndUnitId(Integer id, Integer unitId);
}