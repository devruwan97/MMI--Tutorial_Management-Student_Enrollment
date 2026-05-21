package com.mathsmastery.platform.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitFinalGradeDTO {
    private Integer studentId;
    private String studentName;
    private Double score;
    private String grade;
}