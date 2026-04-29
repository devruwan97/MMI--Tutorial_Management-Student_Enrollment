package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "grade_master")
public class GradeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double minScore;
    private Double maxScore;
    private Double gpaValue;

    @ManyToOne
    @JoinColumn(name = "grade_code_id")
    private GradeCode gradeCode;



}
