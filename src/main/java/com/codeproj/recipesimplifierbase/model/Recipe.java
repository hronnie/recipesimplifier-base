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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recipeId;

    @Column(name = "name")
    private String name;

    @Column(name = "calorie")
    private String calorie;

    @Column(name = "price")
    private Integer price;

    @Column(name = "category")
    private String category;

    @Column(name = "recipe_img_1")
    private String recipeImg1;

    @Column(name = "recipe_img_2")
    private String recipeImg2;

    @Column(name = "recipe_img_3")
    private String recipeImg3;

    @Column(name = "recipe_img_4")
    private String recipeImg4;

    @Column(name = "recipe_img_5")
    private String recipeImg5;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="recipe_id")
    private Set<Ingredient> ingredients;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="recipe_id")
    private Set<Preparation> preparations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="recipe_id")
    private Set<RecipeProcess> processes;

}
