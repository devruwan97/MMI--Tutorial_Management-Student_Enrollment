package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.PaymentRequest;
import com.mathsmastery.platform.model.Payment;
import com.mathsmastery.platform.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payment API", description = "Manage payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Create payment")
    @PostMapping
    public Payment create(@RequestBody PaymentRequest request) {
        return paymentService.createPayment(request);
    }

    @Operation(summary = "Get payments by student")
    @GetMapping("/student/{studentId}")
    public List<Payment> getByStudent(@PathVariable Long studentId) {
        return paymentService.getByStudent(studentId);
    }

    @Operation(summary = "Get payments by enrollment")
    @GetMapping("/enrollment/{enrollmentId}")
    public List<Payment> getByEnrollment(@PathVariable Long enrollmentId) {
        return paymentService.getByEnrollment(enrollmentId);
    }

    @Operation(summary = "Update payment status")
    @PutMapping("/{id}")
    public Payment updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return paymentService.updateStatus(id, status);
    }

    @Operation(summary = "Get all payments")
    @GetMapping
    public List<Payment> getAll() {
        return paymentService.getAll();
    }
}