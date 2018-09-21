package com.codeproj.recipesimplifierbase.rest.validator;

import com.codeproj.recipesimplifierbase.dto.IngredientDto;
import com.codeproj.recipesimplifierbase.dto.PreparationDto;
import com.codeproj.recipesimplifierbase.dto.RecipeDto;
import com.codeproj.recipesimplifierbase.dto.RecipeProcessDto;

import static com.codeproj.recipesimplifierbase.common.Constants.*;
import static com.codeproj.recipesimplifierbase.common.Constants.MIN_SIZE_DURATION;

public class ValidatorBase {

  static boolean isEntityIdValid(Long entityId) {
    return !(entityId == null
      || entityId < 1
    );
  }

  static boolean isIngredientsFail(RecipeDto newRecipe) {
    for (IngredientDto ing : newRecipe.getIngredients()) {
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

  static boolean isPreparationFail(RecipeDto newRecipe) {
    for (PreparationDto prep : newRecipe.getPreparations()) {
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

  static boolean isProcessFail(RecipeDto newRecipe) {
    for (RecipeProcessDto process : newRecipe.getProcesses()) {
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
