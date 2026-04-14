package com.mathsmastery.platform.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentDTO {

    private Integer id;
    private Integer userId;
    private LocalDate dateOfBirth;
    private String parentName;
    private String address;
}