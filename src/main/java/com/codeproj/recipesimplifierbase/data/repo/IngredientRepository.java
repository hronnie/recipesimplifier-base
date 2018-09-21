package com.codeproj.recipesimplifierbase.data.repo;

import com.codeproj.recipesimplifierbase.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
