package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.Student;
import com.mathsmastery.platform.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    long count();

    Optional<Teacher> findByUserId(Long userId);
}

