package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.model.Course;
import com.mathsmastery.platform.service.CourseScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Course Schedule API", description = "Teacher schedule management")
public class CourseScheduleController {

    private final CourseScheduleService service;

    public CourseScheduleController(CourseScheduleService service) {
        this.service = service;
    }

    @Operation(summary = "Get courses assigned to teacher via schedule")
    @GetMapping("/teachers/{teacherId}/schedule/courses")
    public List<Course> getTeacherCourses(@PathVariable Integer teacherId) {
        return service.getCoursesByTeacher(teacherId);
    }
}