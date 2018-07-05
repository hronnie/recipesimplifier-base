package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.common.RecipeCategory;
import com.codeproj.recipesimplifierbase.data.repo.RecipeRepository;
import com.codeproj.recipesimplifierbase.model.Ingredient;
import com.codeproj.recipesimplifierbase.model.Preparation;
import com.codeproj.recipesimplifierbase.model.Recipe;
import com.codeproj.recipesimplifierbase.model.RecipeProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import static com.codeproj.recipesimplifierbase.common.Constants.*;

public class RecipeControllerValidator {

    @Autowired
    private RecipeRepository recipeRepository;

    public static boolean create(Recipe newRecipe) {
        return !(newRecipe == null
                || StringUtils.isEmpty(newRecipe.getName())
                || CollectionUtils.isEmpty(newRecipe.getIngredients())
                || CollectionUtils.isEmpty(newRecipe.getPreparations())
                || CollectionUtils.isEmpty(newRecipe.getProcesses())
                || newRecipe.getCalorie() == null
                || newRecipe.getPrice() == null
                || StringUtils.isEmpty(newRecipe.getCategory())

                || newRecipe.getName().length() > MAX_SIZE_NAME
                || newRecipe.getName().length() < MIN_SIZE_NAME

                || isIngredientsFail(newRecipe)
                || isPreparationFail(newRecipe)
                || isProcessFail(newRecipe)
                || newRecipe.getCalorie() > MAX_SIZE_CALORIE
                || newRecipe.getCalorie() < MIN_SIZE_CALORIE
                || newRecipe.getPrice() > MAX_SIZE_PRICE
                || newRecipe.getPrice() < MIN_SIZE_PRICE
                || newRecipe.getCategory().length() > MAX_SIZE_CATEGORY
                || newRecipe.getCategory().length() < MIN_SIZE_CATEGORY
                || !RecipeCategory.isCategoryValid(newRecipe.getCategory())

        );
    }



    private static boolean isIngredientsFail(Recipe newRecipe) {
        for (Ingredient ing : newRecipe.getIngredients()) {
            if (ing.getName().length() > MAX_SIZE_NAME
                    || ing.getName().length() < MIN_SIZE_NAME
                    || ing.getUnit().length() > MAX_SIZE_UNIT
                    || ing.getUnit().length() < MIN_SIZE_UNIT
                    || ing.getQuantity() > MAX_SIZE_QUANTITY
                    || ing.getQuantity() < MIN_SIZE_QUANTITY
                    ) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPreparationFail(Recipe newRecipe) {
        for (Preparation prep : newRecipe.getPreparations()) {
            if (prep.getDescription().length() > MAX_SIZE_DESCRIPTION
                    || prep.getDescription().length() < MIN_SIZE_DESCRIPTION
                    || prep.getDuration() > MAX_SIZE_DURATION
                    || prep.getDuration() < MIN_SIZE_DURATION
                    ) {
                return true;
            }
        }
        return false;
    }

    private static boolean isProcessFail(Recipe newRecipe) {
        for (RecipeProcess process : newRecipe.getProcesses()) {
            if (process.getDescription().length() > MAX_SIZE_DESCRIPTION
                    || process.getDescription().length() < MIN_SIZE_DESCRIPTION
                    || process.getDuration() > MAX_SIZE_DURATION
                    || process.getDuration() < MIN_SIZE_DURATION
                    ) {
                return true;
            }
        }
        return false;
    }


}
