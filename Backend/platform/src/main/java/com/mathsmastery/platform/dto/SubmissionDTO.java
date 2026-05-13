package com.mathsmastery.platform.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SubmissionDTO {

    private Integer id;

    private Integer assessmentId;

    private Integer studentId;

    private String studentName;

    private String fileName;

    private String fileUrl;

    private String status;

    private LocalDateTime submittedAt;
}