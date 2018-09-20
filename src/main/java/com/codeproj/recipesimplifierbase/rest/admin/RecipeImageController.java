package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.data.repo.RecipeRepository;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping( value = "/api/admin/recipeimage")
public class RecipeImageController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeImageController.class);

    //TODO: remove recipeImage object since we don't need it
//    @Autowired
//    RecipeImageRepository recipeImageRepository;

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

    //TODO: IMPLEMENT FILE DOES EXIST? METHOD

//    @GetMapping("/all")
//    public List<RecipeImage> getRecipeImagesList() {
//        return recipeImageRepository.findAll();
//    }

//    @GetMapping("/{imgName}")
//    public ResponseEntity<byte[]> getRecipeImageByName(@PathVariable String imgName) {
//        RecipeImage recipeImageFile = recipeImageRepository.findRecipeImageByName(imgName);
//
//        if(recipeImageFile != null) {
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recipeImageFile.getName() + "\"")
//                    .body(recipeImageFile.getImageContent());
//        }
//
//        return ResponseEntity.status(404).body(null);
//    }

}
