package com.mathsmastery.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request to enroll a student into a course")
public class EnrollmentRequest {

    @Schema(example = "1")
    private Integer studentId;

    @Schema(example = "2")
    private Integer courseId;
}
