package com.mathsmastery.platform.repository;

import com.mathsmastery.platform.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStudentId(Long studentId);

    List<Payment> findByEnrollmentId(Long enrollmentId);

    long countByStudentIdAndStatus(Long studentId, Payment.Status status);

    @Query("SELECT COALESCE(SUM(p.finalAmount), 0) FROM Payment p WHERE p.status = com.mathsmastery.platform.model.Payment.Status.paid")
    BigDecimal getTotalRevenue();
}
