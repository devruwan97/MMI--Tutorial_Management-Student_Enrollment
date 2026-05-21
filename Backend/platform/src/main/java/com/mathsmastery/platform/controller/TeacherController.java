package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.TeacherRequest;
import com.mathsmastery.platform.dto.TeacherResponse;
import com.mathsmastery.platform.dto.UnitAssignmentRequest;
import com.mathsmastery.platform.model.Unit;
import com.mathsmastery.platform.service.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@Tag(name = "Teacher API", description = "Operations related to teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Operation(summary = "Create teacher profile")
    @PostMapping("/user/{userId}")
    public TeacherResponse create(@PathVariable Integer userId,
                                  @RequestBody TeacherRequest request) {
        return teacherService.createTeacher(userId, request);
    }

    @Operation(summary = "Get all teachers")
    @GetMapping
    public List<TeacherResponse> getAll() {
        return teacherService.getAllTeachers();
    }

    @Operation(summary = "Get teacher by ID")
    @GetMapping("/{id}")
    public TeacherResponse getById(@PathVariable Integer id) {
        return teacherService.getTeacherById(id);
    }

    @Operation(summary = "Update teacher")
    @PutMapping("/{id}")
    public TeacherResponse update(@PathVariable Integer id,
                                  @RequestBody TeacherRequest request) {
        return teacherService.updateTeacher(id, request);
    }

    @Operation(summary = "Delete teacher")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
    }

    @PostMapping("/{teacherId}/courses/{courseId}")
    public void assignCourse(
            @PathVariable Integer teacherId,
            @PathVariable Integer courseId
    ) {
        teacherService.assignCourse(teacherId, courseId);
    }

    @PostMapping("/units/assign")
    public void assignUnits(@RequestBody UnitAssignmentRequest request) {
        teacherService.assignUnits(request);
    }

    @GetMapping("/{teacherId}/assigned-units")
    public List<Unit> getAssignedUnits(
            @PathVariable Integer teacherId
    ) {
        return teacherService.getAssignedUnits(teacherId);
    }
}
