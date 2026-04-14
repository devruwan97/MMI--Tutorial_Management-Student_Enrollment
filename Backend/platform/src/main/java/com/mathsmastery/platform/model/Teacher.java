package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@Schema(description = "Represents a teacher profile linked to a user")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Teacher ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Schema(description = "Associated user with teacher role")
    private User user;

    @Schema(description = "Teacher qualifications", example = "PhD in Mathematics")
    private String qualifications;

    @Schema(description = "Short biography of the teacher", example = "10 years teaching experience")
    private String bio;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Schema(description = "Creation timestamp", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;
}

