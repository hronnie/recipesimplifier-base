package com.codeproj.recipesimplifierbase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="Ingredient")
public class IngredientDto implements Comparable<IngredientDto> {

    public IngredientDto(String name,
                         Integer quantity,
                         String unit,
                         Long ingredientInfoId,
                         String ingredientInfoName,
                         String ingredientInfoDesc) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.ingredientInfoId = ingredientInfoId;
        this.ingredientInfoName = ingredientInfoName;
        this.ingredientInfoDesc = ingredientInfoDesc;
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

    @XmlElement(name = "ingredientInfoName")
    private String ingredientInfoName;

    @XmlElement(name = "ingredientInfoDesc")
    private String ingredientInfoDesc;

    @Override
    public int compareTo(IngredientDto ingredientDto) {
        return ingredientId.compareTo(ingredientDto.getIngredientId());
    }

}