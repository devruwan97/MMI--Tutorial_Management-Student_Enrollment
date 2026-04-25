package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.*;
import com.mathsmastery.platform.model.Payment;
import com.mathsmastery.platform.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DashboardService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final PaymentRepository paymentRepository;

    public DashboardService(
            StudentRepository studentRepository,
            TeacherRepository teacherRepository,
            CourseRepository courseRepository,
            EnrollmentRepository enrollmentRepository,
            PaymentRepository paymentRepository
    ) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.paymentRepository = paymentRepository;
    }

    public AdminDashboardResponse getAdminDashboard() {

        AdminDashboardResponse res = new AdminDashboardResponse();

        res.setTotalStudents(studentRepository.count());
        res.setTotalTeachers(teacherRepository.count());
        res.setTotalCourses(courseRepository.count());
        res.setTotalEnrollments(enrollmentRepository.count());

        BigDecimal revenue = paymentRepository.getTotalRevenue();
        res.setTotalRevenue(revenue != null ? revenue : BigDecimal.ZERO);

        return res;
    }

    public TeacherDashboardResponse getTeacherDashboard(Integer teacherId) {

        TeacherDashboardResponse res = new TeacherDashboardResponse();

        long courses = courseRepository.countByCreatedBy(teacherId);

        long students = enrollmentRepository.count();

        res.setTotalCourses(courses);
        res.setTotalStudents(students);

        return res;
    }

    public StudentDashboardResponse getStudentDashboard(Integer studentId) {

        StudentDashboardResponse res = new StudentDashboardResponse();

        long enrolled = enrollmentRepository.countByStudentId(studentId);

        long pendingPayments =
                paymentRepository.countByStudentIdAndStatus(
                        Long.valueOf(studentId),
                        Payment.Status.pending
                );

        res.setEnrolledCourses(enrolled);
        res.setPendingPayments(pendingPayments);

        return res;
    }
}
