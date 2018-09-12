package com.codeproj.recipesimplifierbase.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="IngredientInfo")
public class IngredientInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ingredientInfoId;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

}
