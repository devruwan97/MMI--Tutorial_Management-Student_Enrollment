package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Getter
@Setter
@Table(name = "siblings")
@Schema(description = "Links student to a sibling group")
public class Sibling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "sibling_group_id")
    private SiblingGroup siblingGroup;
}
