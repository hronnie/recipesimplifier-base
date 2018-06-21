package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.model.Recipe;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class RecipeControllerValidator {

    public static boolean create(Recipe newRecipe) {
        return !(newRecipe == null
                || StringUtils.isEmpty(newRecipe.getName())
                || CollectionUtils.isEmpty(newRecipe.getIngredients())
                || CollectionUtils.isEmpty(newRecipe.getPreparations())
                || CollectionUtils.isEmpty(newRecipe.getProcesses()));
    }

}
