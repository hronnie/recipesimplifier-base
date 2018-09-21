package com.codeproj.recipesimplifierbase.rest.validator;

import static com.codeproj.recipesimplifierbase.common.Constants.*;
import com.codeproj.recipesimplifierbase.dto.IngredientInfoDto;
import org.springframework.util.StringUtils;

public class IngredientInfoValidator extends ValidatorBase {

    public static boolean getIngredientInfoByName(String name) {
        return isIngredientInfoNameValid(name);
    }

    public static boolean deleteByIngredientInfoId(Long ingredientId) {
        return isEntityIdValid(ingredientId);
    }

    public static boolean deleteByName(String name) {
        return isIngredientInfoNameValid(name);
    }

    public static boolean getIngredientInfoById(Long ingredientId) {
        return isEntityIdValid(ingredientId);
    }

    public static boolean create(IngredientInfoDto ingredientInfoDto) {
        return isIngredientInfoNameValid(ingredientInfoDto.getName())
                && isIngredientInfoDescriptionValid(ingredientInfoDto.getDescription());
    }

    private static boolean isIngredientInfoNameValid(String name) {
        return !(name == null
                || StringUtils.isEmpty(name)
                || name.length() > MAX_SIZE_INGREDIENT_INFO_NAME);
    }

    private static boolean isIngredientInfoDescriptionValid(String description) {
        return !(description == null
                || StringUtils.isEmpty(description)
                || description.length() > MAX_SIZE_INGREDIENT_INFO_DESCRIPTION
        );
    }

    public static boolean update(IngredientInfoDto ingredientInfoDto) {
        return isIngredientInfoNameValid(ingredientInfoDto.getName())
                && isEntityIdValid(ingredientInfoDto.getIngredientInfoId())
                && isIngredientInfoDescriptionValid(ingredientInfoDto.getDescription());
    }

}
