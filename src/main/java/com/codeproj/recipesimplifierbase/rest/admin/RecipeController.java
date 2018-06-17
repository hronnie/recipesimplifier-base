package com.codeproj.recipesimplifierbase.rest.admin;

import com.codeproj.recipesimplifierbase.model.Recipe;
import com.codeproj.recipesimplifierbase.model.UserTokenState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class RecipeController {


    @RequestMapping(value = "/admin/newrecipe", method = RequestMethod.POST)
    public ResponseEntity<?> create(
            @RequestBody Recipe newRecipe,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(newRecipe);
    }


}
