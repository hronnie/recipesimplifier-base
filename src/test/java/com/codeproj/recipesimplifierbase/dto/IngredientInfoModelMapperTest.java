package com.codeproj.recipesimplifierbase.dto;

import static org.junit.Assert.assertEquals;
import static com.codeproj.recipesimplifierbase.testhelper.CommonTools.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientInfoModelMapperTest {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkIngredientInfoMapping() {
        IngredientInfoDto ingredientInfoDto = modelMapper.map(ingredientInfo, IngredientInfoDto.class);
        assertEquals(ingredientInfoDto.getIngredientInfoId(), ingredientInfo.getIngredientInfoId());
        assertEquals(ingredientInfoDto.getName(), ingredientInfo.getName());
        assertEquals(ingredientInfoDto.getDescription(), ingredientInfo.getDescription());
    }

}
