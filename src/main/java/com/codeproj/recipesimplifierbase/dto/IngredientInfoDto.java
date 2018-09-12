package com.codeproj.recipesimplifierbase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientInfoDto {

    @XmlElement(name = "id")
    private Long ingredientInfoId;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "description")
    private String description;

}
