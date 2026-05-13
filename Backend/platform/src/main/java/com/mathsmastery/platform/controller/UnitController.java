package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.UnitResponse;
import com.mathsmastery.platform.service.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Course Units API", description = "Manage course units and materials")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @Operation(summary = "Get all units of a course with materials")
    @GetMapping("/{courseId}/units")
    public UnitResponse getUnits(@PathVariable Integer courseId) {
        return unitService.getUnitsByCourse(courseId);
    }
}
