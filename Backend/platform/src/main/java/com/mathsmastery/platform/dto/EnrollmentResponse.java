package com.mathsmastery.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentResponse {

    @Schema(example = "1")
    private Integer id;

    private Integer studentId;
    private Integer courseId;

    @Schema(example = "pending")
    private String status;
}
