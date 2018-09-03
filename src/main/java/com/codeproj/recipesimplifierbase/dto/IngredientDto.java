package com.codeproj.recipesimplifierbase.dto;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@XmlRootElement(name="Ingredient")
public class IngredientDto {


    @XmlElement(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ingredientId;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "quantity")
    private Integer quantity;

    @XmlElement(name = "unit")
    private String unit;

}