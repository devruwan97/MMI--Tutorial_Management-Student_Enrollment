package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.StudentDTO;
import com.mathsmastery.platform.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Create a new student")
    @PostMapping
    public StudentDTO create(@RequestBody StudentDTO dto) {
        return studentService.createStudent(dto);
    }

    @Operation(summary = "Get all students")
    @GetMapping
    public List<StudentDTO> getAll() {
        return studentService.getAllStudents();
    }

    @Operation(summary = "Get student by ID")
    @GetMapping("/{id}")
    public StudentDTO getById(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @Operation(summary = "Delete student")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }

    @Operation(summary = "Get students by course ID")
    @GetMapping("/course/{courseId}")
    public List<StudentDTO> getStudentsByCourse(@PathVariable Integer courseId) {
        return studentService.getStudentsByCourseId(courseId);
    }
}