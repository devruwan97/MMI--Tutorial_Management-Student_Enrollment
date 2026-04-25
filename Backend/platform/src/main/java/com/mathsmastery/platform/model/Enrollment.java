package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "enrollments", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "course_id"})
})
@Schema(description = "Represents student enrollment to a course")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Enrollment ID", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @Schema(description = "Student who enrolled")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @Schema(description = "Course enrolled")
    private Course course;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Enrollment status", example = "pending")
    private Status status = Status.pending;

    @Column(name = "enrolled_at", insertable = false, updatable = false)
    @Schema(description = "Enrollment timestamp")
    private LocalDateTime enrolledAt;

    public enum Status {
        enrolled,
        pending,
        cancelled
    }
}
