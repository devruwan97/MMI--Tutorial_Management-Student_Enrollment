package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.UnitsTeachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitsTeachersRepository extends JpaRepository<UnitsTeachers, Long> {

    List<UnitsTeachers> findByTeacherId(Integer teacherId);

}