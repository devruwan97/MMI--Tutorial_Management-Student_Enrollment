package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.model.Course;
import com.mathsmastery.platform.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Course API", description = "Course management operations")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Create a course for a specific user")
    @PostMapping("/user/{userId}")
    public Course createCourse(@PathVariable Integer userId,
                               @RequestBody Course course) {
        return courseService.createCourse(userId, course);
    }

    @Operation(summary = "Get all courses")
    @GetMapping
    public List<Course> getAll() {
        return courseService.getAllCourses();
    }

    @Operation(summary = "Update a course by ID")
    @PutMapping("/{id}")
    public Course update(@PathVariable Integer id,
                         @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @Operation(summary = "Delete a course by ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }

    @Operation(summary = "Get course by ID")
    @GetMapping("/{id}")
    public Course getById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/student/{userId}/courses")
    public List<Course> getStudentCourses(@PathVariable Integer userId) {
        return courseService.getCoursesByStudent(userId);}

}
