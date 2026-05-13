package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "unit_materials")
public class UnitMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "unit_id")
    private Integer unitId;

    @Column(name = "unit_code")
    private String unitCode;

    private String title;

    private String url;

    private LocalDateTime createdAt;
}
