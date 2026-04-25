package com.mathsmastery.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "sibling_groups")
@Schema(description = "Group of siblings for discount eligibility")
public class SiblingGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String groupName;

    @OneToMany(mappedBy = "siblingGroup")
    private List<Sibling> siblings;
}
