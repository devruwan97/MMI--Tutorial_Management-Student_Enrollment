package com.mathsmastery.platform.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DashboardDTO {

    private Long totalUsers;
    private Long totalStudents;
    private Long totalUnits;
    private Long totalAssessments;
    private Long totalSubmissions;
}
