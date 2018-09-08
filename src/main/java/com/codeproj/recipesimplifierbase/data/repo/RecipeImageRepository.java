package com.codeproj.recipesimplifierbase.data.repo;

import com.codeproj.recipesimplifierbase.model.RecipeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface RecipeImageRepository extends JpaRepository<RecipeImage, Long> {

    public RecipeImage findRecipeImageByName(String name);

}
