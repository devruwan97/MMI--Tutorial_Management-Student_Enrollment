package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.StudentGradeDTO;
import com.mathsmastery.platform.dto.UnitFinalGradeDTO;
import com.mathsmastery.platform.model.StudentGrade;
import com.mathsmastery.platform.service.StudentGradeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class StudentGradeController {

    @Autowired
    private StudentGradeService studentGradeService;

    @PostMapping("/add")
    public StudentGrade addResult(@RequestBody StudentGradeDTO dto,
                                  @RequestParam Integer teacherId) {
        return studentGradeService.addResult(dto, teacherId);
    }

    @GetMapping("/student/{studentId}")
    public List<StudentGrade> getResults(@PathVariable Long studentId) {
        return studentGradeService.getStudentResults(studentId);
    }

    @GetMapping("/unit/{unitId}/final")
    public List<UnitFinalGradeDTO> getFinalGrades(@PathVariable Integer unitId) {
        return studentGradeService.getFinalGradesByUnit(unitId);
    }

}
