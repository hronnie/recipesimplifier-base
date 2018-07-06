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

    public Preparation(String description, Integer duration) {
        this.description = description;
        this.duration = duration;
    }

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