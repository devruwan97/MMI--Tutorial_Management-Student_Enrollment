package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.Sibling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiblingRepository extends JpaRepository<Sibling, Integer> {

    List<Sibling> findByStudentId(Long studentId);

}
