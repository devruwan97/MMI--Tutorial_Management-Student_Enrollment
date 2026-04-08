package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.model.Course;
import com.mathsmastery.platform.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/user/{userId}")
    public Course createCourse(@PathVariable Integer userId,
                               @RequestBody Course course) {
        return courseService.createCourse(userId, course);
    }

    @GetMapping
    public List<Course> getAll() {
        return courseService.getAllCourses();
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Integer id,
                         @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }
}
