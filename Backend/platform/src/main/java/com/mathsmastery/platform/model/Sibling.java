package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "siblings")
@Getter
@Setter
public class Sibling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "sibling_id", nullable = false)
    private Long siblingId;

    @Column(nullable = false)
    private String status; // ACTIVE | INACTIVE

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

}