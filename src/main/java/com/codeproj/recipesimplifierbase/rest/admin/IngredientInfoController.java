package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.data.repo.IngredientInfoRepository;
import com.codeproj.recipesimplifierbase.dto.IngredientInfoDto;
import com.codeproj.recipesimplifierbase.model.IngredientInfo;
import com.codeproj.recipesimplifierbase.rest.admin.RecipeController;
import com.codeproj.recipesimplifierbase.rest.validator.IngredientInfoValidator;
import com.codeproj.recipesimplifierbase.rest.validator.RecipeControllerValidator;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping( value = "/api/admin/ingredientinfo", produces = MediaType.APPLICATION_JSON_VALUE )
public class IngredientInfoController {

    @Autowired
    private IngredientInfoRepository ingredientInfoRepository;

    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
    private static ModelMapper modelMapper = new ModelMapper();

    @GetMapping("byname/{name}")
    public ResponseEntity<?> getIngredientInfoByName(
            @PathVariable("name") String name,
            HttpServletResponse response
    )  {
        if (!IngredientInfoValidator.getIngredientInfoByName(name)) {
            logger.debug("name is not a valid IngredientInfo name");
            return ResponseEntity.unprocessableEntity().build();
        }
        IngredientInfo ingredientInfo = ingredientInfoRepository.findIngredientInfoByName(name);
        if (ingredientInfo == null) {
            logger.debug("name is not a valid IngredientInfo name because it doesn't exist");
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ingredientInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIngredientInfoById(
            @PathVariable("id") Long id,
            HttpServletResponse response
    )  {
        if (!IngredientInfoValidator.getIngredientInfoById(id)) {
            logger.debug("id is not a valid IngredientInfo id");
            return ResponseEntity.unprocessableEntity().build();
        }
        IngredientInfo ingredientInfo = ingredientInfoRepository.findIngredientInfoByIngredientInfoId(id);
        if (ingredientInfo == null) {
            logger.debug("id is not a valid IngredientInfo id because it doesn't exist");
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ingredientInfo);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllIngredientInfo(
            HttpServletResponse response
    )  {

        ModelMapper modelMapper = new ModelMapper();
        List<IngredientInfo> dbResult = ingredientInfoRepository.findAll();
        java.lang.reflect.Type targetListType = new TypeToken<List<IngredientInfoDto>>() {}.getType();
        List<IngredientInfoDto> result = modelMapper.map(dbResult, targetListType);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(
            @RequestBody IngredientInfoDto ingredientInfoDto,
            HttpServletResponse response) {

        if (!IngredientInfoValidator.create(ingredientInfoDto)) {
            logger.debug("ingredientInfoDto is not a valid");
            return ResponseEntity.unprocessableEntity().build();
        }

        IngredientInfo ingredientInfoCheck = ingredientInfoRepository.findIngredientInfoByName(ingredientInfoDto.getName());
        if (ingredientInfoCheck != null) {
            logger.debug("ingredientInfoDto is not a valid because it's already exist by its name");
            return ResponseEntity.unprocessableEntity().build();
        }

        ModelMapper modelMapper = new ModelMapper();
        IngredientInfo result = ingredientInfoRepository.save(modelMapper.map(ingredientInfoDto, IngredientInfo.class));
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().
                path("/{id}").buildAndExpand(result.getIngredientInfoId()).toUri();
        return ResponseEntity.ok(location);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(
            @RequestBody IngredientInfoDto ingredientInfoDto,
            HttpServletResponse response) {

        if (!IngredientInfoValidator.update(ingredientInfoDto)) {
            logger.debug("ingredientInfoDto is not a valid");
            return ResponseEntity.unprocessableEntity().build();
        }

        IngredientInfo ingredientInfoCheck = ingredientInfoRepository.findIngredientInfoByIngredientInfoId(ingredientInfoDto.getIngredientInfoId());
        if (ingredientInfoCheck == null) {
            logger.debug("ingredientInfoDto is not a valid because it doesn't exist");
            return ResponseEntity.unprocessableEntity().build();
        }

        ModelMapper modelMapper = new ModelMapper();
        IngredientInfo result = ingredientInfoRepository.save(modelMapper.map(ingredientInfoDto, IngredientInfo.class));
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().
                path("/{id}").buildAndExpand(result.getIngredientInfoId()).toUri();
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByIngredientInfoId(
            @PathVariable("id") Long indgredientId,
            HttpServletResponse response
    ) {

        if (!IngredientInfoValidator.deleteByIngredientInfoId(indgredientId)) {
            logger.debug("id is not a valid IngredientInfo id");
            return ResponseEntity.unprocessableEntity().build();
        }

        IngredientInfo ingredientInfo = ingredientInfoRepository.findIngredientInfoByIngredientInfoId(indgredientId);
        if (ingredientInfo == null) {
            logger.debug("id is not a valid IngredientInfo id because it doesn't exist");
            return ResponseEntity.noContent().build();
        }

        ingredientInfoRepository.deleteByIngredientInfoId(indgredientId);

        return ResponseEntity.ok("Delete was successful with it: " + indgredientId);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteByName(
            @PathVariable("id") String name,
            HttpServletResponse response
    ) {

        if (!IngredientInfoValidator.deleteByName(name)) {
            logger.debug("id is not a valid IngredientInfo name");
            return ResponseEntity.unprocessableEntity().build();
        }

        IngredientInfo ingredientInfo = ingredientInfoRepository.findIngredientInfoByName(name);
        if (ingredientInfo == null) {
            logger.debug("name is not a valid IngredientInfo name because it doesn't exist");
            return ResponseEntity.noContent().build();
        }

        ingredientInfoRepository.deleteByName(name);

        return ResponseEntity.ok("Delete was successful with it: " + name);
    }

}

