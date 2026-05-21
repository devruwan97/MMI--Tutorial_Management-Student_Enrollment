package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.CourseTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseTeacherRepository extends JpaRepository<CourseTeacher, Long> {

    List<CourseTeacher> findByTeacherId(Long teacherId);

}