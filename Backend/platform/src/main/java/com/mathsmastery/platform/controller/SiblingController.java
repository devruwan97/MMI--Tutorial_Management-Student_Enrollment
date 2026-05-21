package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.model.Sibling;
import com.mathsmastery.platform.model.SiblingRequest;
import com.mathsmastery.platform.service.SiblingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/siblings")
@Tag(name = "Sibling API", description = "Manage sibling discount requests and relationships")
public class SiblingController {

    private final SiblingService siblingService;

    public SiblingController(SiblingService siblingService) {
        this.siblingService = siblingService;
    }

    @Operation(summary = "Request sibling discount")
    @PostMapping("/request")
    public SiblingRequest createRequest(@RequestBody SiblingRequest request) {
        return siblingService.createRequest(request);
    }

    @Operation(summary = "Approve sibling request")
    @PostMapping("/approve/{requestId}")
    public String approveRequest(@PathVariable Long requestId) {
        siblingService.approveRequest(requestId);
        return "Approved successfully";
    }

    @Operation(summary = "Reject sibling request")
    @PostMapping("/reject/{requestId}")
    public String rejectRequest(@PathVariable Long requestId) {
        siblingService.rejectRequest(requestId);
        return "Rejected successfully";
    }

    @Operation(summary = "Get siblings of student")
    @GetMapping("/student/{studentId}")
    public List<Sibling> getByStudent(@PathVariable Long studentId) {
        return siblingService.getByStudent(studentId);
    }

    @GetMapping
    public List<Sibling> getAll() {
        return siblingService.getAllSiblings();
    }

    @GetMapping("allRequests")
    public List<SiblingRequest> getAllSiblingRequests() {
        return siblingService.getAllSiblingsRequests();
    }
}