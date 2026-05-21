package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "units_teachers",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"teacher_id", "course_id", "unit_id"}
        ))
public class UnitsTeachers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_id", nullable = false)
    private Integer teacherId;

    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "unit_id", nullable = false)
    private Integer unitId;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt = LocalDateTime.now();

    public UnitsTeachers() {
    }

    public UnitsTeachers(Integer teacherId, Integer courseId, Integer unitId) {
        this.teacherId = teacherId;
        this.courseId = courseId;
        this.unitId = unitId;
        this.assignedAt = LocalDateTime.now();
    }
}