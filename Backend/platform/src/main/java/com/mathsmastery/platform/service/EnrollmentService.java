package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.EnrollmentRequest;
import com.mathsmastery.platform.model.Course;
import com.mathsmastery.platform.model.Enrollment;
import com.mathsmastery.platform.model.Student;
import com.mathsmastery.platform.repository.CourseRepository;
import com.mathsmastery.platform.repository.EnrollmentRepository;
import com.mathsmastery.platform.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment createEnrollment(EnrollmentRequest request) {

        Student student = studentRepository.findByUserId(Long.valueOf(request.getStudentId()))
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        enrollmentRepository.findByStudentIdAndCourseId(
                student.getId(),
                course.getId()
        ).ifPresent(e -> {
            throw new RuntimeException("Already enrolled in this course");
        });

        long count = enrollmentRepository.findByCourseId(course.getId()).size();

        if (course.getCapacity() != null && count >= course.getCapacity()) {
            throw new RuntimeException("Course is full");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus(Enrollment.Status.pending);

        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getByStudent(Integer userId) {
        Student student = studentRepository.findByUserId(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return enrollmentRepository.findByStudentId(student.getId());
    }

    public List<Enrollment> getByCourse(Integer courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public void delete(Integer id) {
        enrollmentRepository.deleteById(id);
    }
}