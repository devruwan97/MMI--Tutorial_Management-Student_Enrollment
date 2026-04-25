package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Getter
@Setter
@Table(name = "discounts")
@Schema(description = "Discount rules for payments")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private DiscountType type;

    private Double value;

    private String conditionRule;

    private Boolean active;

    public enum DiscountType {
        percentage,
        fixed
    }
}
