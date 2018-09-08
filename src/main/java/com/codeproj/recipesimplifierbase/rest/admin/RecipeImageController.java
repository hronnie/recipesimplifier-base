package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.data.repo.RecipeImageRepository;
import com.codeproj.recipesimplifierbase.model.RecipeImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping( value = "/api/recipeimage")
public class RecipeImageController {

    @Autowired
    RecipeImageRepository recipeImageRepository;

    @PostMapping("/upload")
    public String uploadMultipartFile(@RequestParam("file") MultipartFile file) {
        try {
            RecipeImage recipeImage = new RecipeImage(null, file.getOriginalFilename(), file.getContentType(), file.getBytes());
            recipeImageRepository.save(recipeImage);
            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        } catch (	Exception e) {
            return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
        }
    }

    @GetMapping("/all")
    public List<RecipeImage> getRecipeImagesList() {
        return recipeImageRepository.findAll();
    }

    @GetMapping("/{imgName}")
    public ResponseEntity<byte[]> getRecipeImageByName(@PathVariable String imgName) {
        RecipeImage recipeImageFile = recipeImageRepository.findRecipeImageByName(imgName);

        if(recipeImageFile != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recipeImageFile.getName() + "\"")
                    .body(recipeImageFile.getImageContent());
        }

        return ResponseEntity.status(404).body(null);
    }

}
