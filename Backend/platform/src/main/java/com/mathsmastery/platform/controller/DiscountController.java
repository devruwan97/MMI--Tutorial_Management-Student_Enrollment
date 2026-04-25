package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.DiscountRequest;
import com.mathsmastery.platform.service.DiscountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discounts")
@Tag(name = "Discount API", description = "Sibling & promotional discount system")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Operation(summary = "Calculate discount for student")
    @PostMapping("/calculate")
    public double calculate(@RequestBody DiscountRequest request) {
        return discountService.calculateDiscount(request);
    }

    @Operation(summary = "Get final payable amount")
    @PostMapping("/final-amount")
    public double finalAmount(@RequestBody DiscountRequest request) {
        return discountService.finalAmount(request);
    }
}
