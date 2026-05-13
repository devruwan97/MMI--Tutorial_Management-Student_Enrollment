package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRepository extends JpaRepository<Term, Integer> {
}