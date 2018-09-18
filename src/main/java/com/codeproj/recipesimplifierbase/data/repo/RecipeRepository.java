package com.codeproj.recipesimplifierbase.data.repo;

import com.codeproj.recipesimplifierbase.common.exception.CouldNotFindException;
import com.codeproj.recipesimplifierbase.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    public Recipe findRecipeByName(String name);

    public Recipe findRecipeByRecipeId(Long recipeId);

    public List<Recipe> findRecipesByNameStartsWith(String name);

    @Transactional
    public Recipe save(Recipe recipe);

}
