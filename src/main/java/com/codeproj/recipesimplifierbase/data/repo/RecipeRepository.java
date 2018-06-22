package com.codeproj.recipesimplifierbase.data.repo;

import com.codeproj.recipesimplifierbase.common.exception.CouldNotFindException;
import com.codeproj.recipesimplifierbase.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {


//    default Recipe findByRecipeIdOrFail(Long recipeId) {
//        Recipe recipe = findByRecipeId(recipeId);
//        if (recipe == null) {
//            throw new CouldNotFindException("Could not find recipe " + recipeId);
//        }
//        return recipe;
//    }

    public Recipe findRecipeByName(String name);

    default void create(Recipe newRecipe) {
        save(newRecipe);
    }

}
