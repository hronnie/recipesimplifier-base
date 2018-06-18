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
@Table(name="Ingredient")
public class Ingredient {

    @Id
    @Column(name = "ingredient_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ingredientId;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit")
    private String unit;

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private Recipe recipe;

}