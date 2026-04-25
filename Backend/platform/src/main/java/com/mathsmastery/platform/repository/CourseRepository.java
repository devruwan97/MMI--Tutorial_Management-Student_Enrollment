package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    long countByCreatedBy(Integer createdBy);
}

