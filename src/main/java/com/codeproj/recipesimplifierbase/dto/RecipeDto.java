package com.codeproj.recipesimplifierbase.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="Recipe")
@XmlSeeAlso({IngredientDto.class, PreparationDto.class, RecipeProcessDto.class})
public class RecipeDto {

    @XmlElement(name = "id")
    private Long recipeId;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "calorie")
    private String calorie;

    @XmlElement(name = "price")
    private Integer price;

    @XmlElement(name = "category")
    private String category;

    @XmlElement(name = "ingredients")
    private Set<IngredientDto> ingredients;

    @XmlElement(name = "preparations")
    private Set<PreparationDto> preparations;

    @XmlElement(name = "recipeProcesses")
    private Set<RecipeProcessDto> processes;

}
