package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.data.repo.RecipeRepository;
import com.codeproj.recipesimplifierbase.model.Ingredient;
import com.codeproj.recipesimplifierbase.model.Preparation;
import com.codeproj.recipesimplifierbase.model.Recipe;
import com.codeproj.recipesimplifierbase.model.RecipeProcess;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RecipeControllerTest {

    @Mock
    private RecipeRepository recipeRepository;

    RecipeController recipeController = null;
    Recipe recipe = null;

    Ingredient EMPTY_INGREDIENT = null;
    RecipeProcess EMPTY_PROCESS = null;
    Preparation EMPTY_PREPARATION = null;

    Ingredient VALID_INGREDIENT = null;
    RecipeProcess VALID_PROCESS = null;
    Preparation VALID_PREPARATION = null;

    Set<Ingredient> VALID_INGREDIENTS = null;
    Set<Ingredient> EMPTY_INGREDIENTS = null;
    Set<RecipeProcess> VALID_PROCESSES = null;
    Set<RecipeProcess> EMPTY_PROCESSES = null;
    Set<Preparation> VALID_PREPARATIONS = null;
    Set<Preparation> EMPTY_PREPARATIONS = null;

    public static final String VALID_NAME = "Gulyas";

    @Before
    public void setUp() throws Exception {
        recipeController = new RecipeController();
        recipe = new Recipe();
        EMPTY_INGREDIENT = new Ingredient();
        EMPTY_PROCESS = new RecipeProcess();
        EMPTY_PREPARATION = new Preparation();

        VALID_INGREDIENT = new Ingredient();
        VALID_INGREDIENT.setName("Potato");
        VALID_INGREDIENT.setQuantity(5);
        VALID_INGREDIENT.setUnit("dkg");
        VALID_INGREDIENTS = new HashSet<>();
        VALID_INGREDIENTS.add(VALID_INGREDIENT);
        EMPTY_INGREDIENTS = new HashSet<>();

        VALID_PROCESS = new RecipeProcess();
        VALID_PROCESS.setDescription("Process 1");
        VALID_PROCESS.setDuration(3);
        VALID_PROCESSES = new HashSet<>();
        EMPTY_PROCESSES = new HashSet<>();
        VALID_PROCESSES.add(VALID_PROCESS);

        VALID_PREPARATION = new Preparation();
        VALID_PREPARATION.setDescription("Preparation desc");
        VALID_PREPARATION.setDuration(6);
        VALID_PREPARATIONS = new HashSet<>();
        EMPTY_PREPARATIONS = new HashSet<>();
        VALID_PREPARATIONS.add(VALID_PREPARATION);
    }

    @Test
    public void createWithNullDto() {
        HttpStatus statusCode = recipeController.create(null, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    }

    @Test
    public void createWithMandatoryParameterTest() {
        recipe.setName("");
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(EMPTY_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(EMPTY_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(EMPTY_PROCESSES);
        assertRecipeWithInvalidInput();

        recipe.setName(null);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(null);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(null);
        recipe.setProcesses(VALID_PROCESSES);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(null);
        assertRecipeWithInvalidInput();
    }

    @Test
    public void createWithMinAndMaxLengthCheck() {
        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);

        //Mockito.when(recipeRepository.create(recipe)).thenReturn()
        HttpStatus statusCode = recipeController.create(recipe, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    }

    private void assertRecipeWithInvalidInput() {
        HttpStatus statusCode = recipeController.create(recipe, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    }

}