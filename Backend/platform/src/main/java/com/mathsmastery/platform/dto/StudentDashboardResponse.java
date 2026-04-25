package com.mathsmastery.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Student dashboard overview")
public class StudentDashboardResponse {

    private long enrolledCourses;
    private long pendingPayments;
}
