package com.mathsmastery.platform.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UnitAssessmentResponse {
    private Integer unitId;
    private List<AssessmentDTO> assessments;
}