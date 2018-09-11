package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.data.repo.RecipeImageRepository;
import com.codeproj.recipesimplifierbase.dto.UploadFileResponse;
import com.codeproj.recipesimplifierbase.model.RecipeImage;
import com.codeproj.recipesimplifierbase.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping( value = "/api/recipeimage")
public class RecipeImageController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeImageController.class);

    //TODO: remove recipeImage object since we don't need it
//    @Autowired
//    RecipeImageRepository recipeImageRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/")
    public UploadFileResponse uploadMultipartFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        logger.debug("Image was saved on path: " + fileName);

//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/")
//                .path(fileName)
//                .toUriString();

        return new UploadFileResponse(fileName, "TODO LATER",
                file.getContentType(), file.getSize());
    }

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
