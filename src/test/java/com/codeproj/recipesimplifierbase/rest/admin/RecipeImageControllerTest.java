package com.codeproj.recipesimplifierbase.rest.admin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.codeproj.recipesimplifierbase.util.TestTools.generateNLengthString;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class RecipeImageControllerTest {

  private RecipeImageController recipeImageController = new RecipeImageController();

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void getRecipeImage() throws IOException {
    HttpStatus statusCode = recipeImageController.getRecipeImage(null, null, null).getStatusCode();
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    statusCode = recipeImageController.getRecipeImage(0l, 4, null).getStatusCode();
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    statusCode = recipeImageController.getRecipeImage(3l, 6, null).getStatusCode();
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    statusCode = recipeImageController.getRecipeImage(3l, 0, null).getStatusCode();
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    statusCode = recipeImageController.getRecipeImage(3l, null, null).getStatusCode();
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    statusCode = recipeImageController.getRecipeImage(null, 4, null).getStatusCode();
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
  }

  @Test
  public void getRecipeImagesList() {
    HttpStatus statusCode = recipeImageController.getRecipeImagesList( null).getStatusCode();
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    statusCode = recipeImageController.getRecipeImagesList( 0l).getStatusCode();
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
  }

}
