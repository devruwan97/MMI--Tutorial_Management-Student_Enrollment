package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.AssessmentDTO;
import com.mathsmastery.platform.dto.AssessmentGradeDTO;
import com.mathsmastery.platform.dto.UnitAssessmentResponse;
import com.mathsmastery.platform.model.Assessment;
import com.mathsmastery.platform.model.AssessmentGrade;
import com.mathsmastery.platform.repository.AssessmentGradeRepository;
import com.mathsmastery.platform.repository.AssessmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final AssessmentGradeRepository gradeRepository;

    public AssessmentService(
            AssessmentRepository assessmentRepository,
            AssessmentGradeRepository gradeRepository
    ) {
        this.assessmentRepository = assessmentRepository;
        this.gradeRepository = gradeRepository;
    }
    public UnitAssessmentResponse getAssessmentsByUnit(Integer unitId) {

        List<Assessment> assessments =
                assessmentRepository.findByUnitId(unitId);

        List<AssessmentDTO> assessmentDTOs = new ArrayList<>();

        for (Assessment a : assessments) {

            List<AssessmentGrade> grades =
                    gradeRepository.findByAssessmentId(a.getId());

            List<AssessmentGradeDTO> gradeDTOs = new ArrayList<>();

            for (AssessmentGrade g : grades) {
                AssessmentGradeDTO dto = new AssessmentGradeDTO();
                dto.setStudentId(g.getStudent().getId());
                dto.setMarks(g.getMarks());
                dto.setGrade(g.getGrade());
                gradeDTOs.add(dto);
            }

            AssessmentDTO dto = new AssessmentDTO();
            dto.setId(a.getId());
            dto.setAssessmentCode(a.getAssessmentCode());
            dto.setAssessmentName(a.getAssessmentName());
            dto.setDescription(a.getDescription());
            dto.setMaxMarks(a.getMaxMarks());
            dto.setDueDate(a.getDueDate());
            dto.setGrades(gradeDTOs);

            assessmentDTOs.add(dto);
        }

        UnitAssessmentResponse response = new UnitAssessmentResponse();
        response.setUnitId(unitId);
        response.setAssessments(assessmentDTOs);

        return response;
    }
    public AssessmentDTO getAssessmentDetails(Integer unitId, Integer assessmentId) {

        Optional<Assessment> optional =
                assessmentRepository.findByIdAndUnitId(assessmentId, unitId);

        if (optional.isEmpty()) {
            throw new RuntimeException("Assessment not found");
        }

        Assessment a = optional.get();

        List<AssessmentGrade> grades =
                gradeRepository.findByAssessmentId(a.getId());

        List<AssessmentGradeDTO> gradeDTOs = new ArrayList<>();

        for (AssessmentGrade g : grades) {
            AssessmentGradeDTO dto = new AssessmentGradeDTO();
            dto.setStudentId(g.getStudent().getId());
            dto.setMarks(g.getMarks());
            dto.setGrade(g.getGrade());
            gradeDTOs.add(dto);
        }

        AssessmentDTO dto = new AssessmentDTO();
        dto.setId(a.getId());
        dto.setAssessmentCode(a.getAssessmentCode());
        dto.setAssessmentName(a.getAssessmentName());
        dto.setDescription(a.getDescription());
        dto.setMaxMarks(a.getMaxMarks());
        dto.setDueDate(a.getDueDate());
        dto.setGrades(gradeDTOs);

        return dto;
    }
    public List<AssessmentDTO> getTeacherAssessments(Integer unitId, String status) {

        List<Assessment> assessments =
                assessmentRepository.findByUnitId(unitId);

        List<AssessmentDTO> result = new ArrayList<>();

        for (Assessment a : assessments) {

            AssessmentDTO dto = new AssessmentDTO();
            dto.setId(a.getId());
            dto.setAssessmentCode(a.getAssessmentCode());
            dto.setAssessmentName(a.getAssessmentName());
            dto.setDescription(a.getDescription());
            dto.setMaxMarks(a.getMaxMarks());
            dto.setDueDate(a.getDueDate());

            result.add(dto);
        }

        return result;
    }
    public List<AssessmentDTO> getUpcomingAssessments(Integer unitId) {

        List<Assessment> assessments =
                assessmentRepository.findByUnitId(unitId);

        List<AssessmentDTO> upcoming = new ArrayList<>();

        for (Assessment a : assessments) {

            if (a.getDueDate() != null &&
                    a.getDueDate().isAfter(java.time.LocalDate.now())) {

                AssessmentDTO dto = new AssessmentDTO();
                dto.setId(a.getId());
                dto.setAssessmentCode(a.getAssessmentCode());
                dto.setAssessmentName(a.getAssessmentName());
                dto.setDueDate(a.getDueDate());

                upcoming.add(dto);
            }
        }

        return upcoming;
    }
}