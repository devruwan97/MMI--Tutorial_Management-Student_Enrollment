package com.mathsmastery.platform.service;

import com.mathsmastery.platform.dto.DiscountRequest;
import com.mathsmastery.platform.model.*;
import com.mathsmastery.platform.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {

    private final StudentRepository studentRepository;
    private final SiblingRepository siblingRepository;
    private final DiscountRepository discountRepository;

    public DiscountService(
            StudentRepository studentRepository,
            SiblingRepository siblingRepository,
            DiscountRepository discountRepository
    ) {
        this.studentRepository = studentRepository;
        this.siblingRepository = siblingRepository;
        this.discountRepository = discountRepository;
    }

    public double calculateDiscount(DiscountRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Sibling> siblings = siblingRepository.findByStudentId(Long.valueOf(student.getId()));

        double discount = 0;

        if (!siblings.isEmpty()) {
            discount += request.getAmount() * 0.10; // 10%
        }

        List<Discount> discounts = discountRepository.findByActiveTrue();

        for (Discount d : discounts) {
            if (d.getType() == Discount.DiscountType.percentage) {
                discount += request.getAmount() * (d.getValue() / 100);
            } else {
                discount += d.getValue();
            }
        }

        return discount;
    }

    public double finalAmount(DiscountRequest request) {
        double discount = calculateDiscount(request);
        return request.getAmount() - discount;
    }
}
