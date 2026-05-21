package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String fileName;

    private String fileUrl;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime submittedAt;

    public enum Status {
        PENDING,
        GRADED
    }
}