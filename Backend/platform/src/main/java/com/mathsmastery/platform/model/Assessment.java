package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "assessments")
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "unit_id", insertable = false, updatable = false)
    private Unit unit;

    @Column(name = "assessment_code", nullable = false)
    private String assessmentCode;

    @Column(name = "assessment_name", nullable = false)
    private String assessmentName;

    private String description;

    @Column(name = "max_marks")
    private Integer maxMarks = 100;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "created_at")
    private LocalDate createdAt;
}