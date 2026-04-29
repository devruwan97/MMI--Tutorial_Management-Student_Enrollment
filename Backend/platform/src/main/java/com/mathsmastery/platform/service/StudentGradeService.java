package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.StudentGradeDTO;
import com.mathsmastery.platform.model.*;
import com.mathsmastery.platform.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGradeService {

    private final StudentGradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final UnitRepository unitRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final TeacherRepository teacherRepository;
    private final GradeMasterRepository gradeMasterRepository;

    public StudentGrade addResult(StudentGradeDTO dto, Integer teacherId) {

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Unit unit = unitRepository.findById(dto.getUnitId())
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        Enrollment enrollment = enrollmentRepository.findById(dto.getEnrollmentId())
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        GradeMaster gradeMaster = gradeMasterRepository
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
        grade.setGradeCode(gradeMaster.getGradeCode());
        grade.setGradedAt(LocalDateTime.now());

        return gradeRepository.save(grade);
    }

    public List<StudentGrade> getStudentResults(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }
}
