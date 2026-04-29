package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.StudentGradeDTO;
import com.mathsmastery.platform.model.*;
import com.mathsmastery.platform.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DurationFormat;

import java.time.LocalDateTime;
import java.util.List;

public class StudentGradeService {

    @Autowired private StudentGradeRepository gradeRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private UnitRepository unitRepository;
    @Autowired private EnrollmentRepository enrollmentRepository;
    @Autowired private TeacherRepository teacherRepository;
    @Autowired private GradeMasterRepository gradeMasterRepository;

    // 👨‍🏫 ADD RESULT
    public StudentGrade addResult(StudentGradeDTO dto, Long teacherId) {

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        DurationFormat.Unit unit = unitRepository.findById(dto.getUnitId())
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        Enrollment enrollment = enrollmentRepository.findById(dto.getEnrollmentId())
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        // 🎯 FIND GRADE BASED ON SCORE
        GradeMaster gm = gradeMasterRepository
                .findByMinScoreLessThanEqualAndMaxScoreGreaterThanEqual(
                        dto.getScore(), dto.getScore())
                .orElseThrow(() -> new RuntimeException("Grade range not found"));

        StudentGrade grade = new StudentGrade();
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setUnit(unit);
        grade.setEnrollment(enrollment);
        grade.setTeacher(teacher);
        grade.setScore(dto.getScore());
        grade.setGradeCode(gm.getGradeCode());
        grade.setGradedAt(LocalDateTime.now());

        return gradeRepository.save(grade);
    }


    public List<StudentGrade> getStudentResults(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

}
