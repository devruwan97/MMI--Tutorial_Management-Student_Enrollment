package com.mathsmastery.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Admin dashboard overview")
public class AdminDashboardResponse {

    private long totalStudents;
    private long totalTeachers;
    private long totalCourses;
    private long totalEnrollments;
    private BigDecimal totalRevenue;
}
