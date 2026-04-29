package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.StudentGradeDTO;
import com.mathsmastery.platform.model.StudentGrade;
import com.mathsmastery.platform.service.StudentGradeService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class StudentGradeController {

    @Autowired
    private StudentGradeService service;

    @PostMapping("/add")
    public StudentGrade addResult(@RequestBody StudentGradeDTO dto,
                                  @RequestParam Long teacherId) {
        return service.addResult(dto, teacherId);
    }

    @GetMapping("/student/{studentId}")
    public List<StudentGrade> getResults(@PathVariable Long studentId) {
        return service.getStudentResults(studentId);
    }

}
