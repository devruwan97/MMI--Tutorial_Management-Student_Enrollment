package com.mathsmastery.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request body for creating or updating a teacher")
public class TeacherRequest {

    @Schema(description = "Teacher qualifications")
    private String qualifications;

    @Schema(description = "Teacher biography")
    private String bio;
}
