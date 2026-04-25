package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    default long countStudents() {
        return count();
    }

    default long countByIdInteger(Integer id) {
        return findById(id.longValue()).isPresent() ? 1 : 0;
    }
}