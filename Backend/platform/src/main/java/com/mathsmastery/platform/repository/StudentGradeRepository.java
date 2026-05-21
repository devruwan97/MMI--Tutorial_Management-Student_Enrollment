package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.StudentGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentGradeRepository extends JpaRepository<StudentGrade, Long> {

        List<StudentGrade> findByStudentId(Long studentId);

        List<StudentGrade> findByUnitId(Integer unitId);
}