package com.mathsmastery.platform.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssessmentGradeDTO {

    private Integer id;
    private Integer assessmentId;
    private Integer studentId;

    private Double marks;
    private String grade;
}