package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sibling_requests")
public class SiblingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requesterStudentId;

    private Long targetStudentId;

    private String status; // PENDING / APPROVED / REJECTED
}
