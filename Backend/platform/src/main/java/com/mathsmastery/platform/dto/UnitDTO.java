package com.mathsmastery.platform.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UnitDTO {
    private Integer id;
    private String unitCode;
    private String unitName;
    private String description;

    private Integer termId;
    private String termName;
    private LocalDate termStartDate;
    private LocalDate termEndDate;

    private List<MaterialDTO> materials;
}