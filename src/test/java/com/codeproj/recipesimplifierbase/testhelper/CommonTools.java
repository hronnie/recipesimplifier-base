package com.codeproj.recipesimplifierbase.testhelper;

import com.codeproj.recipesimplifierbase.model.*;

import java.util.HashSet;
import java.util.Set;

public class CommonTools {

    public static final Recipe recipe = new Recipe();

    public static final IngredientInfo ingredientInfo = new IngredientInfo();

    public static final Ingredient ingredient = new Ingredient();
    static {
        ingredient.setIngredientId(3l);
        ingredient.setName("Test ingredient name");
        ingredient.setQuantity(1);
        ingredient.setUnit("Ingredient unit");
        ingredient.setRecipe(recipe);
    }

    public static final Set<Ingredient> ingredients = new HashSet<>();
    static {
        ingredients.add(ingredient);
    }

    public static final RecipeProcess recipeProcess = new RecipeProcess();
    static {
        recipeProcess.setDescription("Recipe process description");
        recipeProcess.setDuration(4);
        recipeProcess.setProcessId(2l);
        recipeProcess.setRecipe(recipe);
    }

    public static final Set<RecipeProcess> recipeProcesses = new HashSet<>();
    static {
        recipeProcesses.add(recipeProcess);
    }

    public static final Preparation preparation = new Preparation();
    static {
        preparation.setPreparationId(6l);
        preparation.setDescription("Preparation description");
        preparation.setDuration(8);
        preparation.setRecipe(recipe);
    }

    public static final Set<Preparation> preparations = new HashSet<>();
    static {
        preparations.add(preparation);

        recipe.setRecipeId(44l);
        recipe.setName("Recipe name");
        recipe.setCategory("Recipe category");
        recipe.setPrice(3000);
        recipe.setCalorie("303");
        recipe.setIngredients(ingredients);
        recipe.setPreparations(preparations);
        recipe.setProcesses(recipeProcesses);
    }

    static {
        ingredientInfo.setIngredientInfoId(44l);
        ingredientInfo.setName("Info name");
        ingredientInfo.setDescription("ingredient info description");
    }

}
