package com.codeproj.recipesimplifierbase.dto;

import static org.junit.Assert.assertEquals;
import static com.codeproj.recipesimplifierbase.testhelper.CommonTools.*;

import com.codeproj.recipesimplifierbase.model.Ingredient;
import com.codeproj.recipesimplifierbase.model.Preparation;
import com.codeproj.recipesimplifierbase.model.RecipeProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeModelMapperTest {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkRecipeMapping() {

        RecipeDto recipeDto = modelMapper.map(recipe, RecipeDto.class);

        assertEquals(recipe.getRecipeId(), recipeDto.getRecipeId());
        assertEquals(recipe.getName(), recipeDto.getName());
        assertEquals(recipe.getCategory(), recipeDto.getCategory());
        assertEquals(recipe.getPrice(), recipeDto.getPrice());
        assertEquals(recipe.getCalorie(), recipeDto.getCalorie());
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
