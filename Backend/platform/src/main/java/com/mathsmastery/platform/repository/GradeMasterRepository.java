package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.GradeMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeMasterRepository extends JpaRepository<GradeMaster, Long> {

    Optional<GradeMaster> findByMinScoreLessThanEqualAndMaxScoreGreaterThanEqual(
            Double score1, Double score2);
}
