package com.codeproj.recipesimplifierbase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="RecipeImageAlbum")
public class RecipeImageAlbumDto {

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

}
