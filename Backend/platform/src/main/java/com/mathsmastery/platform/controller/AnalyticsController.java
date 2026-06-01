package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.DashboardDTO;
import com.mathsmastery.platform.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/dashboard")
    public DashboardDTO getDashboard() {
        return analyticsService.getDashboard();
    }
}
