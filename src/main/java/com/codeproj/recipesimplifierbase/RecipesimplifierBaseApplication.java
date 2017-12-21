package com.codeproj.recipesimplifierbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EntityScan(basePackages = {"com.codeproj.recipesimplifierbase.model"})
@SpringBootApplication
public class RecipesimplifierBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesimplifierBaseApplication.class, args);
	}
}
