package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.StudentGradeDTO;
import com.mathsmastery.platform.dto.UnitFinalGradeDTO;
import com.mathsmastery.platform.model.*;
import com.mathsmastery.platform.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentGradeService {

    private final StudentGradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final UnitRepository unitRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final TeacherRepository teacherRepository;

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

        String gradeMaster = calculateGrade(dto.getScore());

        StudentGrade grade = new StudentGrade();
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setUnit(unit);
        grade.setEnrollment(enrollment);
        grade.setTeacher(teacher);
        grade.setScore(dto.getScore());
        grade.setGradeCode(gradeMaster);
        grade.setGradedAt(LocalDateTime.now());

        return gradeRepository.save(grade);
    }

    public List<StudentGrade> getStudentResults(Long studentId) {
        Optional<Student> student = studentRepository.findByUserId(studentId);
        return gradeRepository.findByStudentId(Long.valueOf(student.get().getId()));
    }

    public List<UnitFinalGradeDTO> getFinalGradesByUnit(Integer unitId) {

        List<StudentGrade> grades =
                gradeRepository.findByUnitId(unitId);

        return grades.stream()
                .collect(Collectors.groupingBy(g -> g.getStudent().getId()))
                .values()
                .stream()
                .map(list -> {
                    StudentGrade latest = list.get(list.size() - 1);

                    UnitFinalGradeDTO dto = new UnitFinalGradeDTO();
                    dto.setStudentId(latest.getStudent().getId());
                    dto.setStudentName(latest.getStudent().getUser().getName());
                    dto.setScore(latest.getScore());
                    dto.setGrade(String.valueOf(latest.getGradeCode()));

                    return dto;
                })
                .toList();
    }

    private String calculateGrade(Double marks) {

        if (marks == null) return "N/A";

        if (marks >= 85) return "A";
        if (marks >= 70) return "B";
        if (marks >= 55) return "C";
        if (marks >= 40) return "D";

        return "F";
    }
}
