package com.mathsmastery.platform.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AssessmentDTO {
    private Integer id;
    private String assessmentCode;
    private String assessmentName;
    private String description;
    private Integer maxMarks;
    private LocalDate dueDate;

    private List<AssessmentGradeDTO> grades;
}