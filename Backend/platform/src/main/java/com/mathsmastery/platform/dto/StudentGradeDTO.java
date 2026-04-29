package com.mathsmastery.platform.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentGradeDTO {

    private Long studentId;
    private Long courseId;
    private Long unitId;
    private Long enrollmentId;
    private Double score;

}
