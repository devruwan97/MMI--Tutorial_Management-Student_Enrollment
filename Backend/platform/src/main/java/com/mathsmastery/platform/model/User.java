package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
@Schema(description = "Represents a system user (admin, student, or teacher)")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique ID of the user", example = "1")
    private Integer id;

    @Schema(description = "Full name of the user", example = "John Doe")
    private String name;

    @Column(unique = true)
    @Schema(description = "User email address", example = "john@email.com")
    private String email;

    @Column(name = "password_hash")
    @Schema(description = "Hashed password", example = "hashed_password_123")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Role of the user", example = "student")
    private Role role;

    @Schema(description = "Phone number", example = "0412345678")
    private String phone;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Schema(description = "Account creation timestamp")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    @Schema(description = "Last updated timestamp")
    private LocalDateTime updatedAt;

    public enum Role {
        admin, student, teacher, management
    }
}
