package com.mathsmastery.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Teacher dashboard overview")
public class TeacherDashboardResponse {

    private long totalCourses;
    private long totalStudents;
}
