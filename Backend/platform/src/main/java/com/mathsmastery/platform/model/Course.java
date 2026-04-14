package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "courses")
@Schema(description = "Represents a course offered in the system")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique course ID", example = "1")
    private Integer id;

    @Schema(description = "Course title", example = "Mathematics Basics")
    private String title;

    @Schema(description = "Course description", example = "Intro to algebra and numbers")
    private String description;

    @Schema(description = "Course category", example = "Mathematics")
    private String category;

    @Schema(description = "Course fee", example = "150.00")
    private BigDecimal fee;

    @Schema(description = "Maximum number of students", example = "30")
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "created_by")
    @Schema(description = "User who created the course")
    private User createdBy;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Schema(description = "Course creation timestamp")
    private LocalDateTime createdAt;
}
