package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.data.repo.RecipeRepository;
import com.codeproj.recipesimplifierbase.dto.GeneralRestResponse;
import com.codeproj.recipesimplifierbase.dto.RecipeImageAlbumDto;
import com.codeproj.recipesimplifierbase.dto.RecipeImageDto;
import com.codeproj.recipesimplifierbase.dto.UploadFileResponse;
import com.codeproj.recipesimplifierbase.model.Recipe;
import com.codeproj.recipesimplifierbase.rest.validator.RecipeImageValidator;
import com.codeproj.recipesimplifierbase.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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


    @GetMapping("/all/{recipeId}")
    public ResponseEntity<?> getRecipeImagesList(@PathVariable("recipeId") Long recipeId) {
        if (!RecipeImageValidator.getRecipeImagesList(recipeId)) {
            logger.debug("recipeId is not valid ");
            return ResponseEntity.unprocessableEntity().build();
        }

        Recipe exsitingRecipe = recipeRepository.findRecipeByRecipeId(recipeId);
        if (exsitingRecipe == null) {
            logger.debug("recipe with the given recipeId doesn't exist");
            return ResponseEntity.unprocessableEntity().build();
        }
        RecipeImageAlbumDto result = new RecipeImageAlbumDto(
                exsitingRecipe.getRecipeImg1(),
                exsitingRecipe.getRecipeImg2(),
                exsitingRecipe.getRecipeImg3(),
                exsitingRecipe.getRecipeImg4(),
                exsitingRecipe.getRecipeImg5()
        );
        return ResponseEntity.ok(result);
    }

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

    @DeleteMapping("/{recipeId}/{index}")
    public ResponseEntity<?> delete(
            @PathVariable("recipeId") Long recipeId,
            @PathVariable("index") Integer index,
            HttpServletResponse response
    )  {

        if (!RecipeImageValidator.delete(index, recipeId)) {
            logger.debug("index or recipeId is not valid ");
            return ResponseEntity.unprocessableEntity().build();
        }

        Recipe exsitingRecipe = recipeRepository.findRecipeByRecipeId(recipeId);
        if (exsitingRecipe == null) {
            logger.debug("recipe with the given recipeId doesn't exist");
            return ResponseEntity.unprocessableEntity().build();
        }

        boolean deleteSuccess = false;
        switch (index) {
            case 1: {
                deleteSuccess = fileStorageService.deleteRecipeImage(exsitingRecipe.getRecipeImg1(), recipeId);
                exsitingRecipe.setRecipeImg1(null);
            } break;
            case 2: {
                deleteSuccess = fileStorageService.deleteRecipeImage(exsitingRecipe.getRecipeImg2(), recipeId);
                exsitingRecipe.setRecipeImg2(null);
            } break;
            case 3: {
                deleteSuccess = fileStorageService.deleteRecipeImage(exsitingRecipe.getRecipeImg3(), recipeId);
                exsitingRecipe.setRecipeImg3(null);
            } break;
            case 4: {
                deleteSuccess = fileStorageService.deleteRecipeImage(exsitingRecipe.getRecipeImg4(), recipeId);
                exsitingRecipe.setRecipeImg4(null);
            } break;
            case 5: {
                deleteSuccess = fileStorageService.deleteRecipeImage(exsitingRecipe.getRecipeImg5(), recipeId);
                exsitingRecipe.setRecipeImg5(null);
            }
        }
        if (!deleteSuccess) {
            return ResponseEntity.noContent().build();
        }

        recipeRepository.save(exsitingRecipe);

        return ResponseEntity.ok(new GeneralRestResponse("Delete was successful with " + recipeId, true));
    }

}
