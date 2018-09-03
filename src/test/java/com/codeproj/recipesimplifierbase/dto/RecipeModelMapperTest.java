package com.codeproj.recipesimplifierbase.dto;

import static org.junit.Assert.assertEquals;

import com.codeproj.recipesimplifierbase.model.Ingredient;
import com.codeproj.recipesimplifierbase.model.Preparation;
import com.codeproj.recipesimplifierbase.model.Recipe;
import com.codeproj.recipesimplifierbase.model.RecipeProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeModelMapperTest {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkRecipeMapping() {
        Recipe recipe = new Recipe();

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(3l);
        ingredient.setName("Test ingredient name");
        ingredient.setQuantity(1);
        ingredient.setUnit("Ingredient unit");
        ingredient.setRecipe(recipe);
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);

        RecipeProcess recipeProcess = new RecipeProcess();
        recipeProcess.setDescription("Recipe process description");
        recipeProcess.setDuration(4);
        recipeProcess.setProcessId(2l);
        recipeProcess.setRecipe(recipe);
        Set<RecipeProcess> recipeProcesses = new HashSet<>();
        recipeProcesses.add(recipeProcess);

        Preparation preparation = new Preparation();
        preparation.setPreparationId(6l);
        preparation.setDescription("Preparation description");
        preparation.setDuration(8);
        preparation.setRecipe(recipe);
        Set<Preparation> preparations = new HashSet<>();
        preparations.add(preparation);

        recipe.setRecipeId(44l);
        recipe.setName("Recipe name");
        recipe.setCategory("Recipe category");
        recipe.setPrice(3000);
        recipe.setCalorie(333);
        recipe.setIngredients(ingredients);
        recipe.setPreparations(preparations);
        recipe.setProcesses(recipeProcesses);

        RecipeDto recipeDto = modelMapper.map(recipe, RecipeDto.class);

        assertEquals(recipe.getRecipeId(), recipeDto.getRecipeId());
        assertEquals(recipe.getName(), recipeDto.getName());
        assertEquals(recipe.getCategory(), recipeDto.getCategory());
        assertEquals(recipe.getPrice(), recipeDto.getPrice());
        assertEquals(recipe.getCalorie(), recipeDto.getCalorie());
        IngredientDto ingredientDto = recipeDto.getIngredients().iterator().next();
        Ingredient ingredientModel = recipe.getIngredients().iterator().next();
        assertEquals(ingredientModel.getIngredientId(), ingredient.getIngredientId());
        assertEquals(ingredientModel.getName(), ingredient.getName());
        assertEquals(ingredientModel.getQuantity(), ingredient.getQuantity());
        assertEquals(ingredientModel.getUnit(), ingredient.getUnit());

        Preparation preparationModel = recipe.getPreparations().iterator().next();
        PreparationDto preparationDto = recipeDto.getPreparations().iterator().next();

        assertEquals(preparationModel.getPreparationId(), preparationDto.getPreparationId());
        assertEquals(preparationModel.getDescription(), preparationDto.getDescription());
        assertEquals(preparationModel.getDuration(), preparationDto.getDuration());

        RecipeProcess recipeProcessModel = recipe.getProcesses().iterator().next();
        RecipeProcessDto recipeProcessDto = recipeDto.getProcesses().iterator().next();

        assertEquals(recipeProcessModel.getProcessId(), recipeProcessDto.getProcessId());
        assertEquals(recipeProcessModel.getDescription(), recipeProcessDto.getDescription());
        assertEquals(recipeProcessModel.getDuration(), recipeProcessDto.getDuration());

    }
}
