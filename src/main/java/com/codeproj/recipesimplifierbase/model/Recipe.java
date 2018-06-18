package com.codeproj.recipesimplifierbase.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Recipe")
public class Recipe {

    @Id
    @Column(name = "recipe_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recipeId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "recipe")
    private Set<Ingredient> ingredients;

    @OneToMany(mappedBy = "recipe")
    private Set<Preparation> preparations;

    @OneToMany(mappedBy = "recipe")
    private Set<Process> processes;

}
