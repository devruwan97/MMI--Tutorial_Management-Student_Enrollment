package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "payments")
@Schema(description = "Represents a payment made by a student")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    private Double amount;

    @Column(name = "discount_applied")
    private Double discountApplied;

    @Column(name = "final_amount")
    private Double finalAmount;

    @Enumerated(EnumType.STRING)
    private Status status = Status.pending;

    @Column(name = "payment_date", insertable = false, updatable = false)
    private LocalDateTime paymentDate;

    public enum Status {
        paid,
        pending,
        failed
    }
}
