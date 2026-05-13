package com.mathsmastery.platform.service;

import com.mathsmastery.platform.model.Course;
import com.mathsmastery.platform.model.Enrollment;
import com.mathsmastery.platform.model.Student;
import com.mathsmastery.platform.model.User;
import com.mathsmastery.platform.repository.CourseRepository;
import com.mathsmastery.platform.repository.EnrollmentRepository;
import com.mathsmastery.platform.repository.StudentRepository;
import com.mathsmastery.platform.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseService(
            CourseRepository courseRepository,
            UserRepository userRepository,
            StudentRepository studentRepository,
            EnrollmentRepository enrollmentRepository
    ) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
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

    public Course getCourseById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public List<Course> getCoursesByStudent(Integer userId) {

        Student student = studentRepository.findByUserId(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(student.getId());

        return enrollments.stream()
                .map(Enrollment::getCourse)
                .toList();
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
                })
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }
}