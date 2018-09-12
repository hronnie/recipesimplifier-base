package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.common.Constants;
import com.codeproj.recipesimplifierbase.dto.IngredientInfoDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static com.codeproj.recipesimplifierbase.util.TestTools.generateNLengthString;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class IngredientInfoControllerTest {

    IngredientInfoController ingredientInfoController = new IngredientInfoController();
    IngredientInfoDto ingredientInfoDto = new IngredientInfoDto();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getIngredientInfoByName() {
        HttpStatus statusCode = ingredientInfoController.getIngredientInfoByName(null, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);

        statusCode = ingredientInfoController.getIngredientInfoByName("", null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    }

    @Test
    public void getIngredientInfoById() {
        HttpStatus statusCode = ingredientInfoController.getIngredientInfoById(null, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);

        statusCode = ingredientInfoController.getIngredientInfoById(0l, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    }

    @Test
    public void deleteByIngredientId() {
        HttpStatus statusCode = ingredientInfoController.deleteByIngredientInfoId(null, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);

        statusCode = ingredientInfoController.deleteByIngredientInfoId(0l, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    }

    @Test
    public void deleteByName() {
        HttpStatus statusCode = ingredientInfoController.deleteByName(null, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);

        statusCode = ingredientInfoController.deleteByName("", null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    }

    @Test
    public void creatIngredientInfoTest() {
        ingredientInfoDto.setIngredientInfoId(null);
        ingredientInfoDto.setName("Name");
        ingredientInfoDto.setDescription("valid ");
        assertCreateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(0l);
        ingredientInfoDto.setName("Name");
        ingredientInfoDto.setDescription("valid ");
        assertCreateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(55l);
        ingredientInfoDto.setName("");
        ingredientInfoDto.setDescription("valid ");
        assertCreateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(55l);
        ingredientInfoDto.setName(null);
        ingredientInfoDto.setDescription("valid ");
        assertCreateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(55l);
        ingredientInfoDto.setName(generateNLengthString(Constants.MAX_SIZE_INGREDIENT_INFO_NAME + 1));
        ingredientInfoDto.setDescription("valid ");
        assertCreateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(55l);
        ingredientInfoDto.setName("Name");
        ingredientInfoDto.setDescription("");
        assertCreateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(55l);
        ingredientInfoDto.setName("Name");
        ingredientInfoDto.setDescription(null);
        assertCreateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(55l);
        ingredientInfoDto.setName("Name");
        ingredientInfoDto.setDescription(generateNLengthString(Constants.MAX_SIZE_INGREDIENT_INFO_DESCRIPTION + 1));
        assertCreateIngredientWithInvalidInput();
    }

    @Test
    public void updateIngredientInfoTest() {
        ingredientInfoDto.setIngredientInfoId(null);
        ingredientInfoDto.setName("Name");
        ingredientInfoDto.setDescription("valid ");
        assertUpdateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(0l);
        ingredientInfoDto.setName("Name");
        ingredientInfoDto.setDescription("valid ");
        assertUpdateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(55l);
        ingredientInfoDto.setName("");
        ingredientInfoDto.setDescription("valid ");
        assertUpdateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(55l);
        ingredientInfoDto.setName(null);
        ingredientInfoDto.setDescription("valid ");
        assertUpdateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(55l);
        ingredientInfoDto.setName(generateNLengthString(Constants.MAX_SIZE_INGREDIENT_INFO_NAME + 1));
        ingredientInfoDto.setDescription("valid ");
        assertUpdateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(55l);
        ingredientInfoDto.setName("Name");
        ingredientInfoDto.setDescription("");
        assertUpdateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(55l);
        ingredientInfoDto.setName("Name");
        ingredientInfoDto.setDescription(null);
        assertUpdateIngredientWithInvalidInput();

        ingredientInfoDto.setIngredientInfoId(55l);
        ingredientInfoDto.setName("Name");
        ingredientInfoDto.setDescription(generateNLengthString(Constants.MAX_SIZE_INGREDIENT_INFO_DESCRIPTION + 1));
        assertUpdateIngredientWithInvalidInput();
    }

    private void assertCreateIngredientWithInvalidInput() {
        HttpStatus statusCode = ingredientInfoController.create(ingredientInfoDto, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    }

    private void assertUpdateIngredientWithInvalidInput() {
        HttpStatus statusCode = ingredientInfoController.update(ingredientInfoDto, null).getStatusCode();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
    }
}
