package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.data.repo.RecipeRepository;
import com.codeproj.recipesimplifierbase.dto.RecipeImageDto;
import com.codeproj.recipesimplifierbase.dto.UploadFileResponse;
import com.codeproj.recipesimplifierbase.model.Recipe;
import com.codeproj.recipesimplifierbase.rest.validator.RecipeImageValidator;
import com.codeproj.recipesimplifierbase.service.FileStorageService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping( value = "/api/admin/recipeimage")
public class RecipeImageController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeImageController.class);

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/{index}/{recipeId}")
    public ResponseEntity<?> uploadRecipeImage(
            @RequestParam("file") MultipartFile file,
            @PathVariable("index") Integer index,
            @PathVariable("recipeId") Long recipeId
            ) {

        if (!RecipeImageValidator.uploadRecipeImage(index, recipeId, file.getContentType())) {
            logger.debug("index or recipeId is not valid or the file wasn't an image");
            return ResponseEntity.unprocessableEntity().build();
        }

        Recipe exsitingRecipe = recipeRepository.findRecipeByRecipeId(recipeId);
        if (exsitingRecipe == null) {
            logger.debug("recipe with the given recipeId doesn't exist");
            return ResponseEntity.unprocessableEntity().build();
        }

        String fileName = fileStorageService.storeFile(file, recipeId);
        logger.debug("Image was saved on path: " + fileName);

        switch (index) {
            case 1: {
                exsitingRecipe.setRecipeImg1(fileName);
            } break;
            case 2: {
                exsitingRecipe.setRecipeImg2(fileName);
            } break;
            case 3: {
                exsitingRecipe.setRecipeImg3(fileName);
            } break;
            case 4: {
                exsitingRecipe.setRecipeImg4(fileName);
            } break;
            case 5: {
                exsitingRecipe.setRecipeImg5(fileName);
            }
        }

        recipeRepository.save(exsitingRecipe);
        return ResponseEntity.ok(new UploadFileResponse(fileName, "TODO LATER",
                file.getContentType(), file.getSize()));
    }


//    @GetMapping("/all")
//    public List<RecipeImage> getRecipeImagesList() {
//        return recipeImageRepository.findAll();
//    }

    @GetMapping("/{recipeId}/{index}")
    public ResponseEntity<byte[]> getRecipeImage(
            @PathVariable("recipeId") Long recipeId,
            @PathVariable("index") Integer index,
            HttpServletRequest request
    ) throws IOException {

        if (!RecipeImageValidator.getRecipeImage(index, recipeId)) {
            logger.debug("index or recipeId is not valid or the file wasn't an image");
            return ResponseEntity.unprocessableEntity().build();
        }

        Recipe exsitingRecipe = recipeRepository.findRecipeByRecipeId(recipeId);
        if (exsitingRecipe == null) {
            logger.debug("recipe with the given recipeId doesn't exist");
            return ResponseEntity.unprocessableEntity().build();
        }

        String fileName = null;

        switch (index) {
            case 1: {
                fileName = exsitingRecipe.getRecipeImg1();
            } break;
            case 2: {
                fileName = exsitingRecipe.getRecipeImg2();
            } break;
            case 3: {
                fileName = exsitingRecipe.getRecipeImg3();
            } break;
            case 4: {
                fileName = exsitingRecipe.getRecipeImg4();
            } break;
            case 5: {
                fileName = exsitingRecipe.getRecipeImg5();
            }
        }

        if (fileName == null || "".equals(fileName)) {
            logger.debug("recipe image with the given index doesn't exist");
            return ResponseEntity.unprocessableEntity().build();
        }
        RecipeImageDto recipeImageDto = fileStorageService.loadRecipeImage(fileName, recipeId);

        return ResponseEntity.ok().contentType(recipeImageDto.getContentType()).body(recipeImageDto.getMedia());
    }

}
