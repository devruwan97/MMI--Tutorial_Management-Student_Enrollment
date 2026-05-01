package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.PaymentRequest;
import com.mathsmastery.platform.model.Enrollment;
import com.mathsmastery.platform.model.Payment;
import com.mathsmastery.platform.model.Student;
import com.mathsmastery.platform.repository.EnrollmentRepository;
import com.mathsmastery.platform.repository.PaymentRepository;
import com.mathsmastery.platform.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            StudentRepository studentRepository,
            EnrollmentRepository enrollmentRepository
    ) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Payment createPayment(PaymentRequest request) {

        Student student = studentRepository.findByUserId(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Enrollment enrollment = enrollmentRepository.findById(Math.toIntExact(request.getEnrollmentId()))
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        double discount = request.getDiscountApplied() != null ? request.getDiscountApplied() : 0;
        double finalAmount = request.getAmount() - discount;

        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setEnrollment(enrollment);
        payment.setAmount(request.getAmount());
        payment.setDiscountApplied(discount);
        payment.setFinalAmount(finalAmount);
        payment.setStatus(Payment.Status.pending);

        return paymentRepository.save(payment);
    }

    public List<Payment> getByStudent(Long studentId) {
        return paymentRepository.findByStudentId(studentId);
    }

    public List<Payment> getByEnrollment(Long enrollmentId) {
        return paymentRepository.findByEnrollmentId(enrollmentId);
    }

    public Payment updateStatus(Long id, String status) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(Payment.Status.valueOf(status));
        return paymentRepository.save(payment);
    }
}
