package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, Integer> {

    List<CourseSchedule> findByTeacherId(Integer teacherId);
}