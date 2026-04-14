package com.mathsmastery.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Response object for teacher")
public class TeacherResponse {

    @Schema(example = "1")
    private Integer id;

    @Schema(example = "1")
    private Integer userId;

    @Schema(example = "Mr Smith")
    private String name;

    @Schema(example = "smith@email.com")
    private String email;

    @Schema(example = "teacher")
    private String role;

    @Schema(example = "PhD in Mathematics")
    private String qualifications;

    @Schema(example = "10 years teaching experience")
    private String bio;

    private LocalDateTime createdAt;
}
