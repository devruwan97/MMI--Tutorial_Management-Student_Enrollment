package com.mathsmastery.platform.service;

import com.mathsmastery.platform.model.Course;
import com.mathsmastery.platform.model.User;
import com.mathsmastery.platform.repository.CourseRepository;
import com.mathsmastery.platform.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course createCourse(Integer userId, Course course) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        course.setCreatedBy(user);

        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course updateCourse(Integer id, Course updated) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setTitle(updated.getTitle());
                    course.setDescription(updated.getDescription());
                    course.setCategory(updated.getCategory());
                    course.setFee(updated.getFee());
                    course.setCapacity(updated.getCapacity());
                    return courseRepository.save(course);
                }).orElseThrow();
    }

    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }
}
