package com.mathsmastery.platform.dto;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "Request to create a payment")
public class PaymentRequest {

    private Long studentId;
    private Long enrollmentId;

    @Schema(example = "200.00")
    private Double amount;

    @Schema(example = "20.00")
    private Double discountApplied;
}
