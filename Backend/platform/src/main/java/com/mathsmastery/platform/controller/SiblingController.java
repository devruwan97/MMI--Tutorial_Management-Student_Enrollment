package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.model.Sibling;
import com.mathsmastery.platform.model.SiblingGroup;
import com.mathsmastery.platform.service.SiblingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/siblings")
@Tag(name = "Sibling API", description = "Manage sibling groups and relationships")
public class SiblingController {

    private final SiblingService siblingService;

    public SiblingController(SiblingService siblingService) {
        this.siblingService = siblingService;
    }

    @Operation(summary = "Create a sibling group")
    @PostMapping("/group")
    public SiblingGroup createGroup(@RequestBody SiblingGroup group) {
        return siblingService.createGroup(group);
    }

    @Operation(summary = "Add student to sibling group")
    @PostMapping("/add")
    public Sibling addSibling(@RequestBody Sibling sibling) {
        return siblingService.addSibling(sibling);
    }

    @Operation(summary = "Get all sibling groups")
    @GetMapping("/groups")
    public List<SiblingGroup> getGroups() {
        return siblingService.getAllGroups();
    }

    @Operation(summary = "Get all sibling links")
    @GetMapping
    public List<Sibling> getAll() {
        return siblingService.getAllSiblings();
    }

    @Operation(summary = "Get sibling group of a student")
    @GetMapping("/student/{studentId}")
    public List<Sibling> getByStudent(@PathVariable Long studentId) {
        return siblingService.getByStudent(studentId);
    }
}
