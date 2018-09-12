package com.codeproj.recipesimplifierbase.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Preparation")
public class Preparation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long preparationId;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne
    private Recipe recipe;
}