package com.codeproj.recipesimplifierbase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Preparation")
public class Preparation {

    @Id
    @Column(name = "preparation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long preparationId;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private String duration;

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private Recipe recipe;
}