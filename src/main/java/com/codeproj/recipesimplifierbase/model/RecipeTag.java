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
@Table(name="RecipeTag")
public class RecipeTag {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ingredientId;

    @Column(name = "name")
    private String name;

    @Column(name = "tag_type")
    private String tagType;

    @Column(name = "order_index")
    private Integer orderIndex;

    @Column(name = "multichoice")
    private Boolean multichoice;

    @ManyToOne
    private Recipe recipe;

}
