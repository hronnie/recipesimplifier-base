package com.codeproj.recipesimplifierbase.dto;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="Ingredient")
public class IngredientDto {

    public IngredientDto(String name, Integer quantity, String unit, Long ingredientInfoId) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.ingredientInfoId = ingredientInfoId;
    }

    @XmlElement(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ingredientId;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "quantity")
    private Integer quantity;

    @XmlElement(name = "unit")
    private String unit;

    @XmlElement(name = "ingredientInfoId")
    private Long ingredientInfoId;

}