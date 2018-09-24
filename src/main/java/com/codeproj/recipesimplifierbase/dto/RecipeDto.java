package com.codeproj.recipesimplifierbase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="RecipeFull")
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

  @XmlElement(name = "recipeImg1")
  private String recipeImg1;

  @XmlElement(name = "recipeImg2")
  private String recipeImg2;

  @XmlElement(name = "recipeImg3")
  private String recipeImg3;

  @XmlElement(name = "recipeImg4")
  private String recipeImg4;

  @XmlElement(name = "recipeImg5")
  private String recipeImg5;

  @XmlElement(name = "ingredients")
  private Set<IngredientDto> ingredients;

  @XmlElement(name = "preparations")
  private Set<PreparationDto> preparations;

  @XmlElement(name = "recipeProcesses")
  private Set<RecipeProcessDto> processes;

}
