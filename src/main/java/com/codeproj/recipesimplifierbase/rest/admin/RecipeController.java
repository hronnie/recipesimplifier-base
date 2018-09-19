package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.data.repo.IngredientRepository;
import com.codeproj.recipesimplifierbase.data.repo.PreparationRepository;
import com.codeproj.recipesimplifierbase.data.repo.RecipeProcessRepository;
import com.codeproj.recipesimplifierbase.data.repo.RecipeRepository;
import com.codeproj.recipesimplifierbase.dto.*;
import com.codeproj.recipesimplifierbase.model.Ingredient;
import com.codeproj.recipesimplifierbase.model.Recipe;
import com.codeproj.recipesimplifierbase.rest.validator.RecipeControllerValidator;
import com.codeproj.recipesimplifierbase.service.FileStorageService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping( value = "/api/admin/recipe", produces = MediaType.APPLICATION_JSON_VALUE )
public class RecipeController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
    private static ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private PreparationRepository preparationRepository;

    @Autowired
    private RecipeProcessRepository recipeProcessRepository;


    @PostMapping("/")
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

        Recipe result = recipeRepository.save(newRecipeModel);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().
                path("byname/{name}").buildAndExpand(result.getName()).toUri();
        return ResponseEntity.ok(newRecipe);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(
            @RequestBody RecipeDto updateRecipe,
            HttpServletResponse response
    )  {
        if (!RecipeControllerValidator.update(updateRecipe)) {
            logger.debug("updateRecipe is not a valid Recipe object");
            return ResponseEntity.unprocessableEntity().build();
        }

        Recipe exsitingRecipe = recipeRepository.findRecipeByRecipeId(updateRecipe.getRecipeId());
        if (exsitingRecipe == null) {
            logger.debug("newRecipe is not a valid Recipe object, recipe doesn't exist");
            return ResponseEntity.unprocessableEntity().build();
        }

        ModelMapper modelMapper = new ModelMapper();
        Recipe updateRecipeModel = modelMapper.map(updateRecipe, Recipe.class);

        updateRecipeModel.getIngredients().stream().forEach(
                ingredient -> {
                    ingredient.setRecipe(updateRecipeModel);
                    if (!exsitingRecipe.getIngredients().contains(ingredient)) {
                        ingredientRepository.save(ingredient);
                    }
                }
        );
        updateRecipeModel.getPreparations().stream().forEach(
                preparation -> {
                    preparation.setRecipe(updateRecipeModel);
                    if (!exsitingRecipe.getPreparations().contains(preparation)) {
                        preparationRepository.save(preparation);
                    }
                }
        );
        updateRecipeModel.getProcesses().stream().forEach(
                process -> {
                    process.setRecipe(updateRecipeModel);
                    if (!exsitingRecipe.getProcesses().contains(process)) {
                        recipeProcessRepository.save(process);
                    }
                }
        );

        Recipe result = recipeRepository.save(updateRecipeModel);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().
                path("byname/{name}").buildAndExpand(result.getName()).toUri();
        return ResponseEntity.ok(updateRecipe);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<?> delete(
            @PathVariable("recipeId") Long recipeId,
            HttpServletResponse response
    )  {
        if (!RecipeControllerValidator.delete(recipeId)) {
            logger.debug("recipeId is not a valid Recipe id");
            return ResponseEntity.unprocessableEntity().build();
        }

        Recipe exsitingRecipe = recipeRepository.findRecipeByRecipeId(recipeId);
        if (exsitingRecipe == null) {
            logger.debug("newRecipe is not a valid Recipe object, recipe doesn't exist");
            return ResponseEntity.unprocessableEntity().build();
        }

        ModelMapper modelMapper = new ModelMapper();
        Recipe deleteRecipeModel = modelMapper.map(exsitingRecipe, Recipe.class);

        recipeRepository.delete(deleteRecipeModel);

        return ResponseEntity.ok(new GeneralRestResponse("Delete was successful with " + recipeId, true));
    }

    @GetMapping("/byname/{name}")
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

        recipeDtoList.stream().forEach(
                recipeDto -> {
                    // Ingredient sort
                    Set<IngredientDto> tempIngrSet = new TreeSet<>();
                    for (IngredientDto item : recipeDto.getIngredients()) {
                        tempIngrSet.add(item);
                    }
                    tempIngrSet.stream().sorted(Comparator.comparing(IngredientDto::getIngredientId));

                    recipeDto.setIngredients(tempIngrSet);

                    // Preparation sort
                    Set<PreparationDto> tempPrepSet = new TreeSet<>();
                    for (PreparationDto item : recipeDto.getPreparations()) {
                        tempPrepSet.add(item);
                    }
                    tempPrepSet.stream().sorted(Comparator.comparing(PreparationDto::getPreparationId));

                    recipeDto.setPreparations(tempPrepSet);

                    // Preparation sort
                    Set<RecipeProcessDto> tempProcSet = new TreeSet<>();
                    for (RecipeProcessDto item : recipeDto.getProcesses()) {
                        tempProcSet.add(item);
                    }
                    tempProcSet.stream().sorted(Comparator.comparing(RecipeProcessDto::getProcessId));

                    recipeDto.setProcesses(tempProcSet);
                }
        );
        return ResponseEntity.ok(recipeDtoList);
    }

}
