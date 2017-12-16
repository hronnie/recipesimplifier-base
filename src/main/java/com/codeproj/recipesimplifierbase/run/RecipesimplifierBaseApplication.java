package com.codeproj.recipesimplifierbase.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.codeproj.model"})
@SpringBootApplication
public class RecipesimplifierBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesimplifierBaseApplication.class, args);
	}
}
