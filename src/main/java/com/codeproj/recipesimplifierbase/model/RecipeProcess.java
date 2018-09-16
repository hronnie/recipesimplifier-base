package com.codeproj.recipesimplifierbase.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="RecipeProcess")
public class RecipeProcess {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long processId;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne
    private Recipe recipe;
}
