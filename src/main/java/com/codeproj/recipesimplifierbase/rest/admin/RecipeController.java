package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.data.repo.RecipeRepository;
import com.codeproj.recipesimplifierbase.dto.RecipeDto;
import com.codeproj.recipesimplifierbase.model.Recipe;
import com.codeproj.recipesimplifierbase.model.UserTokenState;
import com.codeproj.recipesimplifierbase.service.FileStorageService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class RecipeController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
    private static ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/admin/recipe")
    public ResponseEntity<?> create(
            @RequestBody RecipeDto newRecipe,
            HttpServletResponse response
    )  {
        if (!RecipeControllerValidator.create(newRecipe)) {
            logger.debug("newRecipe is not a valid Recipe object");
            return ResponseEntity.unprocessableEntity().build();
        }

        Recipe exsitingRecipe = recipeRepository.findRecipeByName(newRecipe.getName());
        if (exsitingRecipe != null) {
            logger.debug("newRecipe is not a valid Recipe object, name already exist");
            return ResponseEntity.unprocessableEntity().build();
        }

        ModelMapper modelMapper = new ModelMapper();
        Recipe newRecipeModel = modelMapper.map(newRecipe, Recipe.class);

        recipeRepository.create(newRecipeModel);
        return ResponseEntity.ok(newRecipe);
    }

    @GetMapping("/admin/recipe/byname/{name}")
    public ResponseEntity<?> getRecipeListByName(
            @PathVariable("name") String name,
            HttpServletResponse response
    )  {
        List<Recipe> resultList = recipeRepository.findRecipesByNameStartsWith(name);
        if (resultList == null || resultList.isEmpty()) {
            logger.debug("There is no recipe with the given name is not a valid Recipe object, name already exist");
            return ResponseEntity.noContent().build();
        }

        Type listType = new TypeToken<List<RecipeDto>>() {}.getType();
        List<RecipeDto> recipeDtoList = modelMapper.map(resultList, listType);


        return ResponseEntity.ok(recipeDtoList);
    }

}
