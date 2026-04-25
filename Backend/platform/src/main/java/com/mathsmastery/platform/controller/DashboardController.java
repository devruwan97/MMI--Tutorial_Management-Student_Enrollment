package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.*;
import com.mathsmastery.platform.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard API", description = "Role-based dashboard APIs")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/admin")
    @Operation(summary = "Admin dashboard stats")
    public AdminDashboardResponse adminDashboard() {
        return dashboardService.getAdminDashboard();
    }

    @GetMapping("/teacher/{teacherId}")
    @Operation(summary = "Teacher dashboard stats")
    public TeacherDashboardResponse teacherDashboard(@PathVariable Integer teacherId) {
        return dashboardService.getTeacherDashboard(teacherId);
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Student dashboard stats")
    public StudentDashboardResponse studentDashboard(@PathVariable Integer studentId) {
        return dashboardService.getStudentDashboard(studentId);
    }
}
