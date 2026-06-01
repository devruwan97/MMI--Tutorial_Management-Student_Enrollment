package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.DashboardDTO;
import com.mathsmastery.platform.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final UnitRepository unitRepository;
    private final AssessmentRepository assessmentRepository;
    private final SubmissionRepository submissionRepository;

    public DashboardDTO getDashboard() {

        DashboardDTO dto = new DashboardDTO();

        dto.setTotalUsers(userRepository.count());
        dto.setTotalStudents(studentRepository.count());
        dto.setTotalUnits(unitRepository.count());
        dto.setTotalAssessments(assessmentRepository.count());
        dto.setTotalSubmissions(submissionRepository.count());

        return dto;
    }
}
