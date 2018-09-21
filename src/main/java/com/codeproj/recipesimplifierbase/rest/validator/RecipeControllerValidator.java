package com.codeproj.recipesimplifierbase.rest.validator;

import com.codeproj.recipesimplifierbase.common.RecipeCategory;
import com.codeproj.recipesimplifierbase.data.repo.RecipeRepository;
import com.codeproj.recipesimplifierbase.dto.IngredientDto;
import com.codeproj.recipesimplifierbase.dto.PreparationDto;
import com.codeproj.recipesimplifierbase.dto.RecipeDto;
import com.codeproj.recipesimplifierbase.dto.RecipeProcessDto;
import com.codeproj.recipesimplifierbase.model.Ingredient;
import com.codeproj.recipesimplifierbase.model.Preparation;
import com.codeproj.recipesimplifierbase.model.Recipe;
import com.codeproj.recipesimplifierbase.model.RecipeProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import static com.codeproj.recipesimplifierbase.common.Constants.*;

public class RecipeControllerValidator extends ValidatorBase {

    public static boolean create(RecipeDto newRecipe) {
        return !(newRecipe == null
                || StringUtils.isEmpty(newRecipe.getName())
                || CollectionUtils.isEmpty(newRecipe.getIngredients())
                || CollectionUtils.isEmpty(newRecipe.getPreparations())
                || CollectionUtils.isEmpty(newRecipe.getProcesses())
                || newRecipe.getPrice() == null
                || StringUtils.isEmpty(newRecipe.getCategory())

                || newRecipe.getName().length() > MAX_SIZE_NAME
                || newRecipe.getName().length() < MIN_SIZE_NAME

                || isIngredientsFail(newRecipe)
                || isPreparationFail(newRecipe)
                || isProcessFail(newRecipe)
                || newRecipe.getCalorie() == null
                || newRecipe.getCalorie().length() > MAX_SIZE_CALORIE
                || newRecipe.getCalorie().length() < MIN_SIZE_CALORIE
                || newRecipe.getPrice() > MAX_SIZE_PRICE
                || newRecipe.getPrice() < MIN_SIZE_PRICE
                || newRecipe.getCategory().length() > MAX_SIZE_CATEGORY
                || newRecipe.getCategory().length() < MIN_SIZE_CATEGORY
                || !RecipeCategory.isCategoryValid(newRecipe.getCategory())

        );
    }

    public static boolean update(RecipeDto updateRecipe) {
        return create(updateRecipe) && isEntityIdValid(updateRecipe.getRecipeId());
    }

    public static boolean delete(Long recipeId) {
        return isEntityIdValid(recipeId);
    }

}
