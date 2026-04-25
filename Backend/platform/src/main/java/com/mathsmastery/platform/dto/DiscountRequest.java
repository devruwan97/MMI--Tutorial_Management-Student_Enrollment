package com.mathsmastery.platform.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountRequest {

    private Long studentId;
    private Double amount;
}
