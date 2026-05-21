package com.mathsmastery.platform.service;

import com.mathsmastery.platform.model.*;
import com.mathsmastery.platform.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseTeacherRepository courseTeacherRepository;

    public CourseService(
            CourseRepository courseRepository,
            UserRepository userRepository,
            StudentRepository studentRepository,
            EnrollmentRepository enrollmentRepository, TeacherRepository teacherRepository, CourseTeacherRepository courseTeacherRepository
    ) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.courseTeacherRepository = courseTeacherRepository;
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

    public List<Course> getCoursesByTeacher(Integer teacherId) {

        List<CourseTeacher> assignments =
                courseTeacherRepository.findByTeacherId(Long.valueOf(teacherId));

        return assignments.stream()
                .map(CourseTeacher::getCourse)
                .distinct()
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