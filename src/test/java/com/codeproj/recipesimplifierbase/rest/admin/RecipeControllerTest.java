package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.data.repo.RecipeRepository;
import com.codeproj.recipesimplifierbase.model.Ingredient;
import com.codeproj.recipesimplifierbase.model.Preparation;
import com.codeproj.recipesimplifierbase.model.Recipe;
import com.codeproj.recipesimplifierbase.model.RecipeProcess;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static com.codeproj.recipesimplifierbase.util.TestTools.generateNLengthString;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class RecipeControllerTest {

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
    Set<Ingredient> CHANGE_INGREDIENTS = null;
    Set<RecipeProcess> VALID_PROCESSES = null;
    Set<RecipeProcess> EMPTY_PROCESSES = null;
    Set<RecipeProcess> CHANGE_PROCESSES = null;
    Set<Preparation> VALID_PREPARATIONS = null;
    Set<Preparation> EMPTY_PREPARATIONS = null;
    Set<Preparation> CHANGE_PREPARATIONS = null;

    public static final String VALID_NAME = "Gulyas";
    public static final String VALID_CATEGORY = "soup";
    public static final Integer VALID_PRICE = 5000;
    public static final String VALID_CALORIE = "This is a calorie info";

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
        CHANGE_INGREDIENTS = new HashSet<>();
        CHANGE_PREPARATIONS = new HashSet<>();
        CHANGE_PROCESSES = new HashSet<>();

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
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(EMPTY_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(EMPTY_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(EMPTY_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory("");
        assertRecipeWithInvalidInput();


        recipe.setName(null);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(null);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(null);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(null);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(null);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(null);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(null);
        assertRecipeWithInvalidInput();
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
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        Ingredient ing = new Ingredient(generateNLengthString(41), 1, "unit");
        CHANGE_INGREDIENTS.add(ing);
        recipe.setIngredients(CHANGE_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        ing = new Ingredient(VALID_NAME, 5001, "unit");
        CHANGE_INGREDIENTS.clear();
        CHANGE_INGREDIENTS.add(ing);
        recipe.setIngredients(CHANGE_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        ing = new Ingredient(VALID_NAME, 33, generateNLengthString(21));
        CHANGE_INGREDIENTS.clear();
        CHANGE_INGREDIENTS.add(ing);
        recipe.setIngredients(CHANGE_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        Preparation prep = new Preparation(generateNLengthString(501), 5);
        CHANGE_PREPARATIONS.clear();
        CHANGE_PREPARATIONS.add(prep);
        recipe.setPreparations(CHANGE_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        prep = new Preparation("Preparation description", 10001);
        CHANGE_PREPARATIONS.clear();
        CHANGE_PREPARATIONS.add(prep);
        recipe.setPreparations(CHANGE_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        RecipeProcess proc = new RecipeProcess(generateNLengthString(501), 5);
        CHANGE_PROCESSES.clear();
        CHANGE_PROCESSES.add(proc);
        recipe.setProcesses(CHANGE_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        proc = new RecipeProcess("Process description", 10001);
        CHANGE_PROCESSES.clear();
        CHANGE_PROCESSES.add(proc);
        recipe.setProcesses(CHANGE_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(generateNLengthString(201));
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(100001);
        recipe.setCategory(VALID_CATEGORY);
        assertRecipeWithInvalidInput();

        recipe.setName(VALID_NAME);
        recipe.setIngredients(VALID_INGREDIENTS);
        recipe.setPreparations(VALID_PREPARATIONS);
        recipe.setProcesses(VALID_PROCESSES);
        recipe.setCalorie(VALID_CALORIE);
        recipe.setPrice(VALID_PRICE);
        recipe.setCategory(generateNLengthString(51));
        assertRecipeWithInvalidInput();
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
        assertRecipeWithInvalidInput();
    }

    private void assertRecipeWithInvalidInput() {
        HttpStatus statusCode = recipeController.create(recipe, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    }
}