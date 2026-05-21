package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.SiblingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiblingRequestRepository extends JpaRepository<SiblingRequest, Long> {

    List<SiblingRequest> findByRequesterStudentId(Long requesterStudentId);

    List<SiblingRequest> findByTargetStudentId(Long targetStudentId);

    List<SiblingRequest> findByStatus(String status);
}