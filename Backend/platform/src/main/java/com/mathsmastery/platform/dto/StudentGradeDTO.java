package com.mathsmastery.platform.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentGradeDTO {

    private Long studentId;
    private Integer courseId;
    private Integer unitId;
    private Integer enrollmentId;
    private Double score;

}
