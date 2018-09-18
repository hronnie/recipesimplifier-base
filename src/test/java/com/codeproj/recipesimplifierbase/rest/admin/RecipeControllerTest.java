package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.dto.IngredientDto;
import com.codeproj.recipesimplifierbase.dto.PreparationDto;
import com.codeproj.recipesimplifierbase.dto.RecipeDto;
import com.codeproj.recipesimplifierbase.dto.RecipeProcessDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static com.codeproj.recipesimplifierbase.util.TestTools.generateNLengthString;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class RecipeControllerTest {

    RecipeController recipeController = null;
    RecipeDto recipe = null;

    IngredientDto EMPTY_INGREDIENT = null;
    RecipeProcessDto EMPTY_PROCESS = null;
    PreparationDto EMPTY_PREPARATION = null;

    IngredientDto VALID_INGREDIENT = null;
    RecipeProcessDto VALID_PROCESS = null;
    PreparationDto VALID_PREPARATION = null;

    Set<IngredientDto> VALID_INGREDIENTS = null;
    Set<IngredientDto> EMPTY_INGREDIENTS = null;
    Set<IngredientDto> CHANGE_INGREDIENTS = null;
    Set<RecipeProcessDto> VALID_PROCESSES = null;
    Set<RecipeProcessDto> EMPTY_PROCESSES = null;
    Set<RecipeProcessDto> CHANGE_PROCESSES = null;
    Set<PreparationDto> VALID_PREPARATIONS = null;
    Set<PreparationDto> EMPTY_PREPARATIONS = null;
    Set<PreparationDto> CHANGE_PREPARATIONS = null;

    public static final String VALID_NAME = "Gulyas";
    public static final String VALID_CATEGORY = "soup";
    public static final Integer VALID_PRICE = 5000;
    public static final String VALID_CALORIE = "This is a calorie info";

    @Before
    public void setUp() throws Exception {
        recipeController = new RecipeController();
        recipe = new RecipeDto();
        EMPTY_INGREDIENT = new IngredientDto();
        EMPTY_PROCESS = new RecipeProcessDto();
        EMPTY_PREPARATION = new PreparationDto();

        VALID_INGREDIENT = new IngredientDto();
        VALID_INGREDIENT.setName("Potato");
        VALID_INGREDIENT.setQuantity(5);
        VALID_INGREDIENT.setUnit("dkg");
        VALID_INGREDIENTS = new HashSet<>();
        VALID_INGREDIENTS.add(VALID_INGREDIENT);
        EMPTY_INGREDIENTS = new HashSet<>();
        CHANGE_INGREDIENTS = new HashSet<>();
        CHANGE_PREPARATIONS = new HashSet<>();
        CHANGE_PROCESSES = new HashSet<>();

        VALID_PROCESS = new RecipeProcessDto();
        VALID_PROCESS.setDescription("Process 1");
        VALID_PROCESS.setDuration(3);
        VALID_PROCESSES = new HashSet<>();
        EMPTY_PROCESSES = new HashSet<>();
        VALID_PROCESSES.add(VALID_PROCESS);

        VALID_PREPARATION = new PreparationDto();
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
    public void createAndUpdateWithMandatoryParameterTest() {
        recipe.setRecipeId(44l);
        recipe.setName("");
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();
        assertUpdateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(EMPTY_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();
        assertUpdateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(EMPTY_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();
        assertUpdateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(EMPTY_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();
        assertUpdateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory("");
        assertCreateRecipeWithInvalidInput();
        assertUpdateRecipeWithInvalidInput();


        recipe.setName(null);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();
        assertUpdateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(null);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();
        assertUpdateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(null);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();
        assertUpdateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(null);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();
        assertUpdateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(null);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();
        assertUpdateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(null);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();
        assertUpdateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(null);
        assertCreateRecipeWithInvalidInput();
        assertUpdateRecipeWithInvalidInput();

        recipe.setRecipeId(null);
        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertUpdateRecipeWithInvalidInput();

        recipe.setRecipeId(0l);
        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertUpdateRecipeWithInvalidInput();
    }

    @Test
    public void createWithMinAndMaxLengthCheck() {
        recipe.setName(generateNLengthString(41));
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        IngredientDto ing = new IngredientDto(generateNLengthString(41), 1, "unit", 4l);
        CHANGE_INGREDIENTS.add(ing);
        recipe.setIngredients(CHANGE_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        ing = new IngredientDto(VALID_NAME, 5001, "unit", 5l);
        CHANGE_INGREDIENTS.clear();
        CHANGE_INGREDIENTS.add(ing);
        recipe.setIngredients(CHANGE_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        ing = new IngredientDto(VALID_NAME, 33, generateNLengthString(21), 6l);
        CHANGE_INGREDIENTS.clear();
        CHANGE_INGREDIENTS.add(ing);
        recipe.setIngredients(CHANGE_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        PreparationDto prep = new PreparationDto(generateNLengthString(501), 5);
        CHANGE_PREPARATIONS.clear();
        CHANGE_PREPARATIONS.add(prep);
        recipe.setPreparations(CHANGE_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        prep = new PreparationDto("Preparation description", 10001);
        CHANGE_PREPARATIONS.clear();
        CHANGE_PREPARATIONS.add(prep);
        recipe.setPreparations(CHANGE_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        RecipeProcessDto proc = new RecipeProcessDto(generateNLengthString(501), 5);
        CHANGE_PROCESSES.clear();
        CHANGE_PROCESSES.add(proc);
        recipe.setProcesses(CHANGE_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        proc = new RecipeProcessDto("Process description", 10001);
        CHANGE_PROCESSES.clear();
        CHANGE_PROCESSES.add(proc);
        recipe.setProcesses(CHANGE_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(generateNLengthString(201));
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(100001);
        recipe.setCategory(VALID_CATEGORY);
        assertCreateRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(generateNLengthString(51));
        assertCreateRecipeWithInvalidInput();
    }

    @Test
    public void createWithInvalidCategory() {
        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory("random text");
        assertCreateRecipeWithInvalidInput();
    }

    @Test
    public void deleteRecipe() {
        recipe.setRecipeId(null);
        assertDeleteRecipeWithInvalidInput();
        recipe.setRecipeId(0l);
        assertDeleteRecipeWithInvalidInput();
    }

    private void assertCreateRecipeWithInvalidInput() {
        HttpStatus statusCode = recipeController.create(recipe, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    }

    private void assertUpdateRecipeWithInvalidInput() {
        HttpStatus statusCode = recipeController.update(recipe, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    }

    private void assertDeleteRecipeWithInvalidInput() {
        HttpStatus statusCode = recipeController.delete(recipe.getRecipeId(), null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    }
}