package com.codeproj.recipesimplifierbase.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "image-store-path")
@Getter
@Setter
public class RecipeImageFileStorageProperties {

    private String recipeImages;

}
