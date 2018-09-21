package com.codeproj.recipesimplifierbase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.MediaType;


@Data
@AllArgsConstructor
public class RecipeImageDto {

  private byte[] media;
  private MediaType contentType;

}
