package com.codeproj.recipesimplifierbase.data.repo;

import com.codeproj.recipesimplifierbase.common.exception.CouldNotFindException;
import com.codeproj.recipesimplifierbase.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    public Recipe findRecipeByName(String name);

    public List<Recipe> findRecipesByNameStartsWith(String name);

    public Recipe save(Recipe recipe);

}
